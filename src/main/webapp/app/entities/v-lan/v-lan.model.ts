import { BaseEntity } from './../../shared';

export class VLan implements BaseEntity {
    constructor(
        public id?: number,
        public vLanId?: string,
        public vrfId?: number,
        public vLans?: BaseEntity[],
        public domainId?: number,
        public checked?: boolean
    ) {
    }
}
