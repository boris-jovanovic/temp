import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VLanPool } from './v-lan-pool.model';
import { VLanPoolPopupService } from './v-lan-pool-popup.service';
import { VLanPoolService } from './v-lan-pool.service';
import { L2Domain, L2DomainService } from '../l2Domain';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-v-lan-pool-dialog',
    templateUrl: './v-lan-pool-dialog.component.html'
})
export class VLanPoolDialogComponent implements OnInit {

    vLanPool: VLanPool;
    isSaving: boolean;

    l2Domains: L2Domain[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private vLanPoolService: VLanPoolService,
        private l2DomainService: L2DomainService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.l2DomainService.query()
            .subscribe((res: ResponseWrapper) => { this.l2Domains = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vLanPool.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vLanPoolService.update(this.vLanPool));
        } else {
            this.subscribeToSaveResponse(
                this.vLanPoolService.create(this.vLanPool));
        }
    }

    private subscribeToSaveResponse(result: Observable<VLanPool>) {
        result.subscribe((res: VLanPool) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: VLanPool) {
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

    trackL2DomainById(index: number, item: L2Domain) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-v-lan-pool-popup',
    template: ''
})
export class VLanPoolPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vLanPoolPopupService: VLanPoolPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vLanPoolPopupService
                    .open(VLanPoolDialogComponent as Component, params['id']);
            } else {
                this.vLanPoolPopupService
                    .open(VLanPoolDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
