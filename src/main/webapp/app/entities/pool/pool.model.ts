import { BaseEntity } from './../../shared';

export class Pool implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public pools?: BaseEntity[],
        public regionId?: number,
    ) {
    }
}
