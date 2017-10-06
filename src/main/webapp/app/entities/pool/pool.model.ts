import { BaseEntity } from './../../shared';

export class Pool implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public subnet?: string,
        public pools?: BaseEntity[],
        public regionId?: number,
        public vrfId?: number,
    ) {
    }
}
