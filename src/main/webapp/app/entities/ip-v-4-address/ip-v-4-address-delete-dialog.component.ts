import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IpV4Address } from './ip-v-4-address.model';
import { IpV4AddressPopupService } from './ip-v-4-address-popup.service';
import { IpV4AddressService } from './ip-v-4-address.service';

@Component({
    selector: 'jhi-ip-v-4-address-delete-dialog',
    templateUrl: './ip-v-4-address-delete-dialog.component.html'
})
export class IpV4AddressDeleteDialogComponent {

    ipV4Address: IpV4Address;

    constructor(
        private ipV4AddressService: IpV4AddressService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ipV4AddressService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'ipV4AddressListModification',
                content: 'Deleted an ipV4Address'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ip-v-4-address-delete-popup',
    template: ''
})
export class IpV4AddressDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ipV4AddressPopupService: IpV4AddressPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.ipV4AddressPopupService
                .open(IpV4AddressDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
