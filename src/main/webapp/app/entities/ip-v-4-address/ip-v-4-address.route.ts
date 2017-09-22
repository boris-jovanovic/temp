import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { IpV4AddressComponent } from './ip-v-4-address.component';
import { IpV4AddressDetailComponent } from './ip-v-4-address-detail.component';
import { IpV4AddressPopupComponent } from './ip-v-4-address-dialog.component';
import { IpV4AddressDeletePopupComponent } from './ip-v-4-address-delete-dialog.component';

@Injectable()
export class IpV4AddressResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const ipV4AddressRoute: Routes = [
    {
        path: 'ip-v-4-address',
        component: IpV4AddressComponent,
        resolve: {
            'pagingParams': IpV4AddressResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.ipV4Address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ip-v-4-address/:id',
        component: IpV4AddressDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.ipV4Address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ipV4AddressPopupRoute: Routes = [
    {
        path: 'ip-v-4-address-new',
        component: IpV4AddressPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.ipV4Address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ip-v-4-address/:id/edit',
        component: IpV4AddressPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.ipV4Address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ip-v-4-address/:id/delete',
        component: IpV4AddressDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.ipV4Address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
