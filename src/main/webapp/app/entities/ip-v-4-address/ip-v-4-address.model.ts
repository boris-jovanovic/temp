import { BaseEntity } from './../../shared';

export class IpV4Address implements BaseEntity {
    constructor(
        public id?: number,
        public ipV4Address?: string,
        public vLanId?: number,
        public poolId?: number,
    ) {
    }
}
