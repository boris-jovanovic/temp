import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VLanPool } from './v-lan-pool.model';
import { VLanPoolPopupService } from './v-lan-pool-popup.service';
import { VLanPoolService } from './v-lan-pool.service';

@Component({
    selector: 'jhi-v-lan-pool-delete-dialog',
    templateUrl: './v-lan-pool-delete-dialog.component.html'
})
export class VLanPoolDeleteDialogComponent {

    vLanPool: VLanPool;

    constructor(
        private vLanPoolService: VLanPoolService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vLanPoolService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vLanPoolListModification',
                content: 'Deleted an vLanPool'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-v-lan-pool-delete-popup',
    template: ''
})
export class VLanPoolDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vLanPoolPopupService: VLanPoolPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vLanPoolPopupService
                .open(VLanPoolDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
