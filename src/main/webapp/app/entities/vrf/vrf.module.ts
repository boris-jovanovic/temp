import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpamSharedModule } from '../../shared';
import {
    VrfService,
    VrfPopupService,
    VrfComponent,
    VrfDetailComponent,
    VrfDialogComponent,
    VrfPopupComponent,
    VrfDeletePopupComponent,
    VrfDeleteDialogComponent,
    vrfRoute,
    vrfPopupRoute,
} from './';

const ENTITY_STATES = [
    ...vrfRoute,
    ...vrfPopupRoute,
];

@NgModule({
    imports: [
        IpamSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        VrfComponent,
        VrfDetailComponent,
        VrfDialogComponent,
        VrfDeleteDialogComponent,
        VrfPopupComponent,
        VrfDeletePopupComponent,
    ],
    entryComponents: [
        VrfComponent,
        VrfDialogComponent,
        VrfPopupComponent,
        VrfDeleteDialogComponent,
        VrfDeletePopupComponent,
    ],
    providers: [
        VrfService,
        VrfPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamVrfModule {}
