import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { VrfComponent } from './vrf.component';
import { VrfDetailComponent } from './vrf-detail.component';
import { VrfPopupComponent } from './vrf-dialog.component';
import { VrfDeletePopupComponent } from './vrf-delete-dialog.component';

export const vrfRoute: Routes = [
    {
        path: 'vrf',
        component: VrfComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vrf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'vrf/:id',
        component: VrfDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vrf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vrfPopupRoute: Routes = [
    {
        path: 'vrf-new',
        component: VrfPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vrf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vrf/:id/edit',
        component: VrfPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vrf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'vrf/:id/delete',
        component: VrfDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vrf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
