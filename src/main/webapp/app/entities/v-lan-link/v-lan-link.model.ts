import { BaseEntity } from './../../shared';
import { VLan } from './../v-lan';

export class VLanLink implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public vLanLinks?: BaseEntity[],
        public vLans?: VLan[],
    ) {
    }
}
