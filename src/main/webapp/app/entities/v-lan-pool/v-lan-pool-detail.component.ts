import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { VLanPool } from './v-lan-pool.model';
import { VLanPoolService } from './v-lan-pool.service';

@Component({
    selector: 'jhi-v-lan-pool-detail',
    templateUrl: './v-lan-pool-detail.component.html'
})
export class VLanPoolDetailComponent implements OnInit, OnDestroy {

    vLanPool: VLanPool;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private vLanPoolService: VLanPoolService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVLanPools();
    }

    load(id) {
        this.vLanPoolService.find(id).subscribe((vLanPool) => {
            this.vLanPool = vLanPool;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVLanPools() {
        this.eventSubscriber = this.eventManager.subscribe(
            'vLanPoolListModification',
            (response) => this.load(this.vLanPool.id)
        );
    }
}
