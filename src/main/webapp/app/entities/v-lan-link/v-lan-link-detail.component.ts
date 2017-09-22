import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { VLanLink } from './v-lan-link.model';
import { VLanLinkService } from './v-lan-link.service';

@Component({
    selector: 'jhi-v-lan-link-detail',
    templateUrl: './v-lan-link-detail.component.html'
})
export class VLanLinkDetailComponent implements OnInit, OnDestroy {

    vLanLink: VLanLink;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private vLanLinkService: VLanLinkService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVLanLinks();
    }

    load(id) {
        this.vLanLinkService.find(id).subscribe((vLanLink) => {
            this.vLanLink = vLanLink;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVLanLinks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vLanLinkListModification',
            (response) => this.load(this.vLanLink.id)
        );
    }
}
