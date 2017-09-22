/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { IpamTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { VLanLinkDetailComponent } from '../../../../../../main/webapp/app/entities/v-lan-link/v-lan-link-detail.component';
import { VLanLinkService } from '../../../../../../main/webapp/app/entities/v-lan-link/v-lan-link.service';
import { VLanLink } from '../../../../../../main/webapp/app/entities/v-lan-link/v-lan-link.model';

describe('Component Tests', () => {

    describe('VLanLink Management Detail Component', () => {
        let comp: VLanLinkDetailComponent;
        let fixture: ComponentFixture<VLanLinkDetailComponent>;
        let service: VLanLinkService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [IpamTestModule],
                declarations: [VLanLinkDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    VLanLinkService,
                    JhiEventManager
                ]
            }).overrideTemplate(VLanLinkDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VLanLinkDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VLanLinkService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new VLanLink(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.vLanLink).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
