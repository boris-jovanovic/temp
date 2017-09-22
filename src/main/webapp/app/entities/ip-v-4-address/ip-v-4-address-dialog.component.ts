import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IpV4Address } from './ip-v-4-address.model';
import { IpV4AddressPopupService } from './ip-v-4-address-popup.service';
import { IpV4AddressService } from './ip-v-4-address.service';
import { VLan, VLanService } from '../v-lan';
import { Pool, PoolService } from '../pool';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ip-v-4-address-dialog',
    templateUrl: './ip-v-4-address-dialog.component.html'
})
export class IpV4AddressDialogComponent implements OnInit {

    ipV4Address: IpV4Address;
    isSaving: boolean;

    vlans: VLan[];

    pools: Pool[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private ipV4AddressService: IpV4AddressService,
        private vLanService: VLanService,
        private poolService: PoolService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.vLanService.query()
            .subscribe((res: ResponseWrapper) => { this.vlans = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.poolService.query()
            .subscribe((res: ResponseWrapper) => { this.pools = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.ipV4Address.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ipV4AddressService.update(this.ipV4Address));
        } else {
            this.subscribeToSaveResponse(
                this.ipV4AddressService.create(this.ipV4Address));
        }
    }

    private subscribeToSaveResponse(result: Observable<IpV4Address>) {
        result.subscribe((res: IpV4Address) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: IpV4Address) {
        this.eventManager.broadcast({ name: 'ipV4AddressListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackVLanById(index: number, item: VLan) {
        return item.id;
    }

    trackPoolById(index: number, item: Pool) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-ip-v-4-address-popup',
    template: ''
})
export class IpV4AddressPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ipV4AddressPopupService: IpV4AddressPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ipV4AddressPopupService
                    .open(IpV4AddressDialogComponent as Component, params['id']);
            } else {
                this.ipV4AddressPopupService
                    .open(IpV4AddressDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
