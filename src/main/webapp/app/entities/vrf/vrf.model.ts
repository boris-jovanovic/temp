import { BaseEntity } from './../../shared';

export class Vrf implements BaseEntity {
    constructor(
        public id?: number,
        public vrfId?: string,
        public vrfs?: BaseEntity[],
    ) {
    }
}
