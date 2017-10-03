import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpamSharedModule } from '../../shared';
import {
    L2DomainService,
    L2DomainPopupService,
    L2DomainComponent,
    L2DomainDetailComponent,
    L2DomainDialogComponent,
    L2DomainPopupComponent,
    L2DomainDeletePopupComponent,
    L2DomainDeleteDialogComponent,
    l2DomainRoute,
    l2DomainPopupRoute,
} from './';

const ENTITY_STATES = [
    ...l2DomainRoute,
    ...l2DomainPopupRoute,
];

@NgModule({
    imports: [
        IpamSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        L2DomainComponent,
        L2DomainDetailComponent,
        L2DomainDialogComponent,
        L2DomainDeleteDialogComponent,
        L2DomainPopupComponent,
        L2DomainDeletePopupComponent,
    ],
    entryComponents: [
        L2DomainComponent,
        L2DomainDialogComponent,
        L2DomainPopupComponent,
        L2DomainDeleteDialogComponent,
        L2DomainDeletePopupComponent,
    ],
    providers: [
        L2DomainService,
        L2DomainPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamL2DomainModule {}
