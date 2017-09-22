import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Vrf } from './vrf.model';
import { VrfPopupService } from './vrf-popup.service';
import { VrfService } from './vrf.service';

@Component({
    selector: 'jhi-vrf-delete-dialog',
    templateUrl: './vrf-delete-dialog.component.html'
})
export class VrfDeleteDialogComponent {

    vrf: Vrf;

    constructor(
        private vrfService: VrfService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vrfService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'vrfListModification',
                content: 'Deleted an vrf'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vrf-delete-popup',
    template: ''
})
export class VrfDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private vrfPopupService: VrfPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.vrfPopupService
                .open(VrfDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
