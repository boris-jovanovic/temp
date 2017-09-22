import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { VLan } from './v-lan.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class VLanService {

    private resourceUrl = SERVER_API_URL + 'api/v-lans';

    constructor(private http: Http) { }

    create(vLan: VLan): Observable<VLan> {
        const copy = this.convert(vLan);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(vLan: VLan): Observable<VLan> {
        const copy = this.convert(vLan);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<VLan> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(vLan: VLan): VLan {
        const copy: VLan = Object.assign({}, vLan);
        return copy;
    }
}
