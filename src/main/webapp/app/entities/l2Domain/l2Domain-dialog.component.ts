import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { L2Domain } from './l2Domain.model';
import { L2DomainPopupService } from './l2Domain-popup.service';
import { L2DomainService } from './l2Domain.service';
import { Region, RegionService } from '../region';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-l2Domain-dialog',
    templateUrl: './l2Domain-dialog.component.html'
})
export class L2DomainDialogComponent implements OnInit {

    l2Domain: L2Domain;
    isSaving: boolean;

    regions: Region[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private l2DomainService: L2DomainService,
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
        if (this.l2Domain.id !== undefined) {
            this.subscribeToSaveResponse(
                this.l2DomainService.update(this.l2Domain));
        } else {
            this.subscribeToSaveResponse(
                this.l2DomainService.create(this.l2Domain));
        }
    }

    private subscribeToSaveResponse(result: Observable<L2Domain>) {
        result.subscribe((res: L2Domain) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: L2Domain) {
        this.eventManager.broadcast({ name: 'l2DomainListModification', content: 'OK'});
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
    selector: 'jhi-l2Domain-popup',
    template: ''
})
export class L2DomainPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private l2DomainPopupService: L2DomainPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.l2DomainPopupService
                    .open(L2DomainDialogComponent as Component, params['id']);
            } else {
                this.l2DomainPopupService
                    .open(L2DomainDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
