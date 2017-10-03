import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { L2DomainComponent } from './l2Domain.component';
import { L2DomainDetailComponent } from './l2Domain-detail.component';
import { L2DomainPopupComponent } from './l2Domain-dialog.component';
import { L2DomainDeletePopupComponent } from './l2Domain-delete-dialog.component';

export const l2DomainRoute: Routes = [
    {
        path: 'l2Domain',
        component: L2DomainComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.l2Domain.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'l2Domain/:id',
        component: L2DomainDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.l2Domain.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const l2DomainPopupRoute: Routes = [
    {
        path: 'l2Domain-new',
        component: L2DomainPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.l2Domain.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'l2Domain/:id/edit',
        component: L2DomainPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.l2Domain.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'l2Domain/:id/delete',
        component: L2DomainDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ipamApp.l2Domain.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
