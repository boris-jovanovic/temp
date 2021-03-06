package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.domain.Vrf;
import jhipster.monolithic.angular.service.dto.VLanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-10T10:22:44+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class VLanMapperImpl implements VLanMapper {

    @Autowired
    private VrfMapper vrfMapper;
    @Autowired
    private VLanLinkMapper vLanLinkMapper;
    @Autowired
    private VLanPoolMapper vLanPoolMapper;

    @Override
    public List<VLanDTO> toDto(List<VLan> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<VLanDTO> list = new ArrayList<VLanDTO>();
        for ( VLan vLan : arg0 ) {
            list.add( toDto( vLan ) );
        }

        return list;
    }

    @Override
    public List<VLan> toEntity(List<VLanDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<VLan> list = new ArrayList<VLan>();
        for ( VLanDTO vLanDTO : arg0 ) {
            list.add( toEntity( vLanDTO ) );
        }

        return list;
    }

    @Override
    public VLanDTO toDto(VLan vLan) {
        if ( vLan == null ) {
            return null;
        }

        VLanDTO vLanDTO_ = new VLanDTO();

        vLanDTO_.setVrfId( vLanVrfId( vLan ) );
        vLanDTO_.setVLanPoolId( vLanVLanPoolId( vLan ) );
        vLanDTO_.setVLanLinkId( vLanVLanLinkId( vLan ) );
        vLanDTO_.setId( vLan.getId() );
        vLanDTO_.setVLanId( vLan.getVLanId() );

        return vLanDTO_;
    }

    @Override
    public VLan toEntity(VLanDTO vLanDTO) {
        if ( vLanDTO == null ) {
            return null;
        }

        VLan vLan_ = new VLan();

        vLan_.setVrf( vrfMapper.fromId( vLanDTO.getVrfId() ) );
        vLan_.setVLanLink( vLanLinkMapper.fromId( vLanDTO.getVLanLinkId() ) );
        vLan_.setVLanPool( vLanPoolMapper.fromId( vLanDTO.getVLanPoolId() ) );
        vLan_.setId( vLanDTO.getId() );
        vLan_.setVLanId( vLanDTO.getVLanId() );

        return vLan_;
    }

    private Long vLanVrfId(VLan vLan) {

        if ( vLan == null ) {
            return null;
        }
        Vrf vrf = vLan.getVrf();
        if ( vrf == null ) {
            return null;
        }
        Long id = vrf.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long vLanVLanPoolId(VLan vLan) {

        if ( vLan == null ) {
            return null;
        }
        VLanPool vLanPool = vLan.getVLanPool();
        if ( vLanPool == null ) {
            return null;
        }
        Long id = vLanPool.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long vLanVLanLinkId(VLan vLan) {

        if ( vLan == null ) {
            return null;
        }
        VLanLink vLanLink = vLan.getVLanLink();
        if ( vLanLink == null ) {
            return null;
        }
        Long id = vLanLink.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
