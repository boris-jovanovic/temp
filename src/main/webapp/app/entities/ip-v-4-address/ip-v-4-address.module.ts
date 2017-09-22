import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpamSharedModule } from '../../shared';
import {
    IpV4AddressService,
    IpV4AddressPopupService,
    IpV4AddressComponent,
    IpV4AddressDetailComponent,
    IpV4AddressDialogComponent,
    IpV4AddressPopupComponent,
    IpV4AddressDeletePopupComponent,
    IpV4AddressDeleteDialogComponent,
    ipV4AddressRoute,
    ipV4AddressPopupRoute,
    IpV4AddressResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...ipV4AddressRoute,
    ...ipV4AddressPopupRoute,
];

@NgModule({
    imports: [
        IpamSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        IpV4AddressComponent,
        IpV4AddressDetailComponent,
        IpV4AddressDialogComponent,
        IpV4AddressDeleteDialogComponent,
        IpV4AddressPopupComponent,
        IpV4AddressDeletePopupComponent,
    ],
    entryComponents: [
        IpV4AddressComponent,
        IpV4AddressDialogComponent,
        IpV4AddressPopupComponent,
        IpV4AddressDeleteDialogComponent,
        IpV4AddressDeletePopupComponent,
    ],
    providers: [
        IpV4AddressService,
        IpV4AddressPopupService,
        IpV4AddressResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamIpV4AddressModule {}
