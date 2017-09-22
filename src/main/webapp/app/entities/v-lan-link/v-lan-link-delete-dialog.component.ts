import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VLanLink } from './v-lan-link.model';
import { VLanLinkPopupService } from './v-lan-link-popup.service';
import { VLanLinkService } from './v-lan-link.service';

@Component({
    selector: 'jhi-v-lan-link-delete-dialog',
    templateUrl: './v-lan-link-delete-dialog.component.html'
})
export class VLanLinkDeleteDialogComponent {

    vLanLink: VLanLink;

    constructor(
        private vLanLinkService: VLanLinkService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vLanLinkService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vLanLinkListModification',
                content: 'Deleted an vLanLink'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-v-lan-link-delete-popup',
    template: ''
})
export class VLanLinkDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vLanLinkPopupService: VLanLinkPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vLanLinkPopupService
                .open(VLanLinkDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
