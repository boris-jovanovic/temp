import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { IpamVrfModule } from './vrf/vrf.module';
import { IpamVLanModule } from './v-lan/v-lan.module';
import { IpamIpV4AddressModule } from './ip-v-4-address/ip-v-4-address.module';
import { IpamPoolModule } from './pool/pool.module';
import { IpamVLanLinkModule } from './v-lan-link/v-lan-link.module';
import { IpamL2DomainModule } from './l2Domain/l2Domain.module';
import { IpamRegionModule } from './region/region.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        IpamVrfModule,
        IpamVLanModule,
        IpamIpV4AddressModule,
        IpamPoolModule,
        IpamVLanLinkModule,
        IpamL2DomainModule,
        IpamRegionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpamEntityModule {}
