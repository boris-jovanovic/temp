/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { IpamTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { VrfDetailComponent } from '../../../../../../main/webapp/app/entities/vrf/vrf-detail.component';
import { VrfService } from '../../../../../../main/webapp/app/entities/vrf/vrf.service';
import { Vrf } from '../../../../../../main/webapp/app/entities/vrf/vrf.model';

describe('Component Tests', () => {

    describe('Vrf Management Detail Component', () => {
        let comp: VrfDetailComponent;
        let fixture: ComponentFixture<VrfDetailComponent>;
        let service: VrfService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpamTestModule],
                declarations: [VrfDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    VrfService,
                    JhiEventManager
                ]
            }).overrideTemplate(VrfDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VrfDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VrfService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Vrf(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.vrf).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
