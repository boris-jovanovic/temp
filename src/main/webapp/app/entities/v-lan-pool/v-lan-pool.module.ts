import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpamSharedModule } from '../../shared';
import {
    VLanPoolService,
    VLanPoolPopupService,
    VLanPoolComponent,
    VLanPoolDetailComponent,
    VLanPoolDialogComponent,
    VLanPoolPopupComponent,
    VLanPoolDeletePopupComponent,
    VLanPoolDeleteDialogComponent,
    vLanPoolRoute,
    vLanPoolPopupRoute,
} from './';

const ENTITY_STATES = [
    ...vLanPoolRoute,
    ...vLanPoolPopupRoute,
];

@NgModule({
    imports: [
        IpamSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        VLanPoolComponent,
        VLanPoolDetailComponent,
        VLanPoolDialogComponent,
        VLanPoolDeleteDialogComponent,
        VLanPoolPopupComponent,
        VLanPoolDeletePopupComponent,
    ],
    entryComponents: [
        VLanPoolComponent,
        VLanPoolDialogComponent,
        VLanPoolPopupComponent,
        VLanPoolDeleteDialogComponent,
        VLanPoolDeletePopupComponent,
    ],
    providers: [
        VLanPoolService,
        VLanPoolPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamVLanPoolModule {}
