import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Vrf } from './vrf.model';
import { VrfPopupService } from './vrf-popup.service';
import { VrfService } from './vrf.service';

@Component({
    selector: 'jhi-vrf-dialog',
    templateUrl: './vrf-dialog.component.html'
})
export class VrfDialogComponent implements OnInit {

    vrf: Vrf;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private vrfService: VrfService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vrf.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vrfService.update(this.vrf));
        } else {
            this.subscribeToSaveResponse(
                this.vrfService.create(this.vrf));
        }
    }

    private subscribeToSaveResponse(result: Observable<Vrf>) {
        result.subscribe((res: Vrf) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Vrf) {
        this.eventManager.broadcast({ name: 'vrfListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-vrf-popup',
    template: ''
})
export class VrfPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vrfPopupService: VrfPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vrfPopupService
                    .open(VrfDialogComponent as Component, params['id']);
            } else {
                this.vrfPopupService
                    .open(VrfDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
