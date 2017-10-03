import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { L2Domain } from './l2Domain.model';
import { L2DomainPopupService } from './l2Domain-popup.service';
import { L2DomainService } from './l2Domain.service';

@Component({
    selector: 'jhi-l2Domain-delete-dialog',
    templateUrl: './l2Domain-delete-dialog.component.html'
})
export class L2DomainDeleteDialogComponent {

    l2Domain: L2Domain;

    constructor(
        private l2DomainService: L2DomainService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.l2DomainService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'l2DomainListModification',
                content: 'Deleted an L2 Domain'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-l2Domain-delete-popup',
    template: ''
})
export class L2DomainDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private l2DomainPopupService: L2DomainPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.l2DomainPopupService
                .open(L2DomainDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
