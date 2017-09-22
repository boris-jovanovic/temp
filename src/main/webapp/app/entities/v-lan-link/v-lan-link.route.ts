import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { VLanLinkComponent } from './v-lan-link.component';
import { VLanLinkDetailComponent } from './v-lan-link-detail.component';
import { VLanLinkPopupComponent } from './v-lan-link-dialog.component';
import { VLanLinkDeletePopupComponent } from './v-lan-link-delete-dialog.component';

export const vLanLinkRoute: Routes = [
    {
        path: 'v-lan-link',
        component: VLanLinkComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanLink.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'v-lan-link/:id',
        component: VLanLinkDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanLink.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vLanLinkPopupRoute: Routes = [
    {
        path: 'v-lan-link-new',
        component: VLanLinkPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanLink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'v-lan-link/:id/edit',
        component: VLanLinkPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanLink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'v-lan-link/:id/delete',
        component: VLanLinkDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.vLanLink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
