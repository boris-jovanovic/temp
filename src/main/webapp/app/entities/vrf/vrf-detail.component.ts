import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Vrf } from './vrf.model';
import { VrfService } from './vrf.service';

@Component({
    selector: 'jhi-vrf-detail',
    templateUrl: './vrf-detail.component.html'
})
export class VrfDetailComponent implements OnInit, OnDestroy {

    vrf: Vrf;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private vrfService: VrfService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVrfs();
    }

    load(id) {
        this.vrfService.find(id).subscribe((vrf) => {
            this.vrf = vrf;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVrfs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vrfListModification',
            (response) => this.load(this.vrf.id)
        );
    }
}
