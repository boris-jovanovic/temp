import { BaseEntity } from './../../shared';

export class L2Domain implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public l2Domains?: BaseEntity[]
    ) {
    }
}
