import { BaseEntity } from './../../shared';

export class VLanLink implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public vLanLinks?: BaseEntity[],
    ) {
    }
}
