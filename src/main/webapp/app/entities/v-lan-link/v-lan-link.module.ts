import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpamSharedModule } from '../../shared';
import {
    VLanLinkService,
    VLanLinkPopupService,
    VLanLinkComponent,
    VLanLinkDetailComponent,
    VLanLinkDialogComponent,
    VLanLinkPopupComponent,
    VLanLinkDeletePopupComponent,
    VLanLinkDeleteDialogComponent,
    vLanLinkRoute,
    vLanLinkPopupRoute,
} from './';

const ENTITY_STATES = [
    ...vLanLinkRoute,
    ...vLanLinkPopupRoute,
];

@NgModule({
    imports: [
        IpamSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        VLanLinkComponent,
        VLanLinkDetailComponent,
        VLanLinkDialogComponent,
        VLanLinkDeleteDialogComponent,
        VLanLinkPopupComponent,
        VLanLinkDeletePopupComponent,
    ],
    entryComponents: [
        VLanLinkComponent,
        VLanLinkDialogComponent,
        VLanLinkPopupComponent,
        VLanLinkDeleteDialogComponent,
        VLanLinkDeletePopupComponent,
    ],
    providers: [
        VLanLinkService,
        VLanLinkPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamVLanLinkModule {}
