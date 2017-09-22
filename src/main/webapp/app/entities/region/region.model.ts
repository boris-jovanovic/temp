import { BaseEntity } from './../../shared';

export class Region implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public regionToVLans?: BaseEntity[],
        public regionToPools?: BaseEntity[],
    ) {
    }
}
