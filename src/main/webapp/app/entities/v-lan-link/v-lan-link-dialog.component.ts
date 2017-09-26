import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VLanLink } from './v-lan-link.model';
import { VLanLinkPopupService } from './v-lan-link-popup.service';
import { VLanLinkService } from './v-lan-link.service';
import { VLan, VLanService } from '../v-lan';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-v-lan-link-dialog',
    templateUrl: './v-lan-link-dialog.component.html'
})
export class VLanLinkDialogComponent implements OnInit {

    vLanLink: VLanLink;
    isSaving: boolean;

	vLans: VLan[];
	
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private vLanLinkService: VLanLinkService,
        private vLanService: VLanService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.vLanService.query()
        	.subscribe((res: ResponseWrapper) => { this.vLanLink.vLans = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.vLanLink.id !== undefined) {
            this.subscribeToSaveResponse(
                this.vLanLinkService.update(this.vLanLink));
        } else {
            this.subscribeToSaveResponse(
                this.vLanLinkService.create(this.vLanLink));
        }
    }

    private subscribeToSaveResponse(result: Observable<VLanLink>) {
        result.subscribe((res: VLanLink) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: VLanLink) {
        this.eventManager.broadcast({ name: 'vLanLinkListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-v-lan-link-popup',
    template: ''
})
export class VLanLinkPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vLanLinkPopupService: VLanLinkPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.vLanLinkPopupService
                    .open(VLanLinkDialogComponent as Component, params['id']);
            } else {
                this.vLanLinkPopupService
                    .open(VLanLinkDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
