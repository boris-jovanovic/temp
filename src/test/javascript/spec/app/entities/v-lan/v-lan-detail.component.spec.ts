/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { IpamTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { VLanDetailComponent } from '../../../../../../main/webapp/app/entities/v-lan/v-lan-detail.component';
import { VLanService } from '../../../../../../main/webapp/app/entities/v-lan/v-lan.service';
import { VLan } from '../../../../../../main/webapp/app/entities/v-lan/v-lan.model';

describe('Component Tests', () => {

    describe('VLan Management Detail Component', () => {
        let comp: VLanDetailComponent;
        let fixture: ComponentFixture<VLanDetailComponent>;
        let service: VLanService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpamTestModule],
                declarations: [VLanDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    VLanService,
                    JhiEventManager
                ]
            }).overrideTemplate(VLanDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VLanDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VLanService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new VLan(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.vLan).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
