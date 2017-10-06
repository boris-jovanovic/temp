import { BaseEntity } from './../../shared';

export class VLanPool implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public domainId?: number,
        public vLans?: BaseEntity[]
    ) {
    }
}
