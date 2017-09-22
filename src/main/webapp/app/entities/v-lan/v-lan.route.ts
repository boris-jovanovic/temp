import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { VLanComponent } from './v-lan.component';
import { VLanDetailComponent } from './v-lan-detail.component';
import { VLanPopupComponent } from './v-lan-dialog.component';
import { VLanDeletePopupComponent } from './v-lan-delete-dialog.component';

export const vLanRoute: Routes = [
    {
        path: 'v-lan',
        component: VLanComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLan.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'v-lan/:id',
        component: VLanDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLan.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vLanPopupRoute: Routes = [
    {
        path: 'v-lan-new',
        component: VLanPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLan.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'v-lan/:id/edit',
        component: VLanPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLan.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'v-lan/:id/delete',
        component: VLanDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLan.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
