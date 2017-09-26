package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-09-26T13:10:21+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class VLanLinkMapperImpl implements VLanLinkMapper {

    @Override
    public VLanLinkDTO toDto(VLanLink arg0) {
        if ( arg0 == null ) {
            return null;
        }

        VLanLinkDTO vLanLinkDTO = new VLanLinkDTO();

        vLanLinkDTO.setId( arg0.getId() );
        vLanLinkDTO.setName( arg0.getName() );

        return vLanLinkDTO;
    }

    @Override
    public List<VLanLinkDTO> toDto(List<VLanLink> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<VLanLinkDTO> list = new ArrayList<VLanLinkDTO>();
        for ( VLanLink vLanLink : arg0 ) {
            list.add( toDto( vLanLink ) );
        }

        return list;
    }

    @Override
    public List<VLanLink> toEntity(List<VLanLinkDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<VLanLink> list = new ArrayList<VLanLink>();
        for ( VLanLinkDTO vLanLinkDTO : arg0 ) {
            list.add( toEntity( vLanLinkDTO ) );
        }

        return list;
    }

    @Override
    public VLanLink toEntity(VLanLinkDTO vLanLinkDTO) {
        if ( vLanLinkDTO == null ) {
            return null;
        }

        VLanLink vLanLink_ = new VLanLink();

        vLanLink_.setId( vLanLinkDTO.getId() );
        vLanLink_.setName( vLanLinkDTO.getName() );

        return vLanLink_;
    }
}
