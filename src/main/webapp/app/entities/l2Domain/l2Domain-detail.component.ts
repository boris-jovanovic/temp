import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { L2Domain } from './l2Domain.model';
import { L2DomainService } from './l2Domain.service';

@Component({
    selector: 'jhi-l2Domain-detail',
    templateUrl: './l2Domain-detail.component.html'
})
export class L2DomainDetailComponent implements OnInit, OnDestroy {

    l2Domain: L2Domain;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private l2DomainService: L2DomainService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInL2Domains();
    }

    load(id) {
        this.l2DomainService.find(id).subscribe((l2Domain) => {
            this.l2Domain = l2Domain;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInL2Domains() {
        this.eventSubscriber = this.eventManager.subscribe(
            'l2DomainListModification',
            (response) => this.load(this.l2Domain.id)
        );
    }
}
