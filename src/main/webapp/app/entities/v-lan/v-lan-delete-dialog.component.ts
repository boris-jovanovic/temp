import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VLan } from './v-lan.model';
import { VLanPopupService } from './v-lan-popup.service';
import { VLanService } from './v-lan.service';

@Component({
    selector: 'jhi-v-lan-delete-dialog',
    templateUrl: './v-lan-delete-dialog.component.html'
})
export class VLanDeleteDialogComponent {

    vLan: VLan;

    constructor(
        private vLanService: VLanService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vLanService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vLanListModification',
                content: 'Deleted an vLan'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-v-lan-delete-popup',
    template: ''
})
export class VLanDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vLanPopupService: VLanPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vLanPopupService
                .open(VLanDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
