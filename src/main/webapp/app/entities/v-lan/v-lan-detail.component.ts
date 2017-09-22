import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { VLan } from './v-lan.model';
import { VLanService } from './v-lan.service';

@Component({
    selector: 'jhi-v-lan-detail',
    templateUrl: './v-lan-detail.component.html'
})
export class VLanDetailComponent implements OnInit, OnDestroy {

    vLan: VLan;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private vLanService: VLanService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVLans();
    }

    load(id) {
        this.vLanService.find(id).subscribe((vLan) => {
            this.vLan = vLan;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVLans() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vLanListModification',
            (response) => this.load(this.vLan.id)
        );
    }
}
