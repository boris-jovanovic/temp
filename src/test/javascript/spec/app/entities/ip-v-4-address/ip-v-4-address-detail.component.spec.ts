/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { IpamTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { IpV4AddressDetailComponent } from '../../../../../../main/webapp/app/entities/ip-v-4-address/ip-v-4-address-detail.component';
import { IpV4AddressService } from '../../../../../../main/webapp/app/entities/ip-v-4-address/ip-v-4-address.service';
import { IpV4Address } from '../../../../../../main/webapp/app/entities/ip-v-4-address/ip-v-4-address.model';

describe('Component Tests', () => {

    describe('IpV4Address Management Detail Component', () => {
        let comp: IpV4AddressDetailComponent;
        let fixture: ComponentFixture<IpV4AddressDetailComponent>;
        let service: IpV4AddressService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpamTestModule],
                declarations: [IpV4AddressDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    IpV4AddressService,
                    JhiEventManager
                ]
            }).overrideTemplate(IpV4AddressDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IpV4AddressDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IpV4AddressService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new IpV4Address(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.ipV4Address).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
