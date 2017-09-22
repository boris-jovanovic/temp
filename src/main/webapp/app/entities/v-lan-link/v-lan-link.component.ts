import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { VLanLink } from './v-lan-link.model';
import { VLanLinkService } from './v-lan-link.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-v-lan-link',
    templateUrl: './v-lan-link.component.html'
})
export class VLanLinkComponent implements OnInit, OnDestroy {
vLanLinks: VLanLink[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private vLanLinkService: VLanLinkService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.vLanLinkService.query().subscribe(
            (res: ResponseWrapper) => {
                this.vLanLinks = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInVLanLinks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: VLanLink) {
        return item.id;
    }
    registerChangeInVLanLinks() {
        this.eventSubscriber = this.eventManager.subscribe('vLanLinkListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
