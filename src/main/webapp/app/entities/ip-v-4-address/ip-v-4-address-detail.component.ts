import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { IpV4Address } from './ip-v-4-address.model';
import { IpV4AddressService } from './ip-v-4-address.service';

@Component({
    selector: 'jhi-ip-v-4-address-detail',
    templateUrl: './ip-v-4-address-detail.component.html'
})
export class IpV4AddressDetailComponent implements OnInit, OnDestroy {

    ipV4Address: IpV4Address;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private ipV4AddressService: IpV4AddressService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInIpV4Addresses();
    }

    load(id) {
        this.ipV4AddressService.find(id).subscribe((ipV4Address) => {
            this.ipV4Address = ipV4Address;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInIpV4Addresses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'ipV4AddressListModification',
            (response) => this.load(this.ipV4Address.id)
        );
    }
}
