import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { IpV4Address } from './ip-v-4-address.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class IpV4AddressService {

    private resourceUrl = SERVER_API_URL + 'api/ip-v-4-addresses';

    constructor(private http: Http) { }

    create(ipV4Address: IpV4Address): Observable<IpV4Address> {
        const copy = this.convert(ipV4Address);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(ipV4Address: IpV4Address): Observable<IpV4Address> {
        const copy = this.convert(ipV4Address);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<IpV4Address> {
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

    private convert(ipV4Address: IpV4Address): IpV4Address {
        const copy: IpV4Address = Object.assign({}, ipV4Address);
        return copy;
    }
}
