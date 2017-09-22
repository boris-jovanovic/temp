import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpamSharedModule } from '../../shared';
import {
    VLanService,
    VLanPopupService,
    VLanComponent,
    VLanDetailComponent,
    VLanDialogComponent,
    VLanPopupComponent,
    VLanDeletePopupComponent,
    VLanDeleteDialogComponent,
    vLanRoute,
    vLanPopupRoute,
} from './';

const ENTITY_STATES = [
    ...vLanRoute,
    ...vLanPopupRoute,
];

@NgModule({
    imports: [
        IpamSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        VLanComponent,
        VLanDetailComponent,
        VLanDialogComponent,
        VLanDeleteDialogComponent,
        VLanPopupComponent,
        VLanDeletePopupComponent,
    ],
    entryComponents: [
        VLanComponent,
        VLanDialogComponent,
        VLanPopupComponent,
        VLanDeleteDialogComponent,
        VLanDeletePopupComponent,
    ],
    providers: [
        VLanService,
        VLanPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamVLanModule {}
