import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Pool } from './pool.model';
import { PoolPopupService } from './pool-popup.service';
import { PoolService } from './pool.service';
import { Region, RegionService } from '../region';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-pool-dialog',
    templateUrl: './pool-dialog.component.html'
})
export class PoolDialogComponent implements OnInit {

    pool: Pool;
    isSaving: boolean;

    regions: Region[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private poolService: PoolService,
        private regionService: RegionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.regionService.query()
            .subscribe((res: ResponseWrapper) => { this.regions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.pool.id !== undefined) {
            this.subscribeToSaveResponse(
                this.poolService.update(this.pool));
        } else {
            this.subscribeToSaveResponse(
                this.poolService.create(this.pool));
        }
    }

    private subscribeToSaveResponse(result: Observable<Pool>) {
        result.subscribe((res: Pool) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Pool) {
        this.eventManager.broadcast({ name: 'poolListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackRegionById(index: number, item: Region) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-pool-popup',
    template: ''
})
export class PoolPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private poolPopupService: PoolPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.poolPopupService
                    .open(PoolDialogComponent as Component, params['id']);
            } else {
                this.poolPopupService
                    .open(PoolDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
