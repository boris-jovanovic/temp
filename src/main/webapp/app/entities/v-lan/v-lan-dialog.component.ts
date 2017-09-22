import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VLan } from './v-lan.model';
import { VLanPopupService } from './v-lan-popup.service';
import { VLanService } from './v-lan.service';
import { Vrf, VrfService } from '../vrf';
import { VLanLink, VLanLinkService } from '../v-lan-link';
import { Region, RegionService } from '../region';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-v-lan-dialog',
    templateUrl: './v-lan-dialog.component.html'
})
export class VLanDialogComponent implements OnInit {

    vLan: VLan;
    isSaving: boolean;

    vrfs: Vrf[];

    vlanlinks: VLanLink[];

    regions: Region[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private vLanService: VLanService,
        private vrfService: VrfService,
        private vLanLinkService: VLanLinkService,
        private regionService: RegionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.vrfService.query()
            .subscribe((res: ResponseWrapper) => { this.vrfs = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.vLanLinkService.query()
            .subscribe((res: ResponseWrapper) => { this.vlanlinks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.regionService.query()
            .subscribe((res: ResponseWrapper) => { this.regions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vLan.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vLanService.update(this.vLan));
        } else {
            this.subscribeToSaveResponse(
                this.vLanService.create(this.vLan));
        }
    }

    private subscribeToSaveResponse(result: Observable<VLan>) {
        result.subscribe((res: VLan) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: VLan) {
        this.eventManager.broadcast({ name: 'vLanListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackVrfById(index: number, item: Vrf) {
        return item.id;
    }

    trackVLanLinkById(index: number, item: VLanLink) {
        return item.id;
    }

    trackRegionById(index: number, item: Region) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-v-lan-popup',
    template: ''
})
export class VLanPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vLanPopupService: VLanPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vLanPopupService
                    .open(VLanDialogComponent as Component, params['id']);
            } else {
                this.vLanPopupService
                    .open(VLanDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
