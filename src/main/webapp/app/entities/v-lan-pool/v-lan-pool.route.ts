import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { VLanPoolComponent } from './v-lan-pool.component';
import { VLanPoolDetailComponent } from './v-lan-pool-detail.component';
import { VLanPoolPopupComponent } from './v-lan-pool-dialog.component';
import { VLanPoolDeletePopupComponent } from './v-lan-pool-delete-dialog.component';

export const vLanPoolRoute: Routes = [
    {
        path: 'v-lan-pool',
        component: VLanPoolComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanPool.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'v-lan-pool/:id',
        component: VLanPoolDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanPool.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vLanPoolPopupRoute: Routes = [
    {
        path: 'v-lan-pool-new',
        component: VLanPoolPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanPool.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'v-lan-pool/:id/edit',
        component: VLanPoolPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanPool.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'v-lan-pool/:id/delete',
        component: VLanPoolDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanPool.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
