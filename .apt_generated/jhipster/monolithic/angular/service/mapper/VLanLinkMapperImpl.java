package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-04T14:33:01+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class VLanLinkMapperImpl implements VLanLinkMapper {

    @Override
    public VLanLinkDTO toDto(VLanLink entity) {
        if ( entity == null ) {
            return null;
        }

        VLanLinkDTO vLanLinkDTO = new VLanLinkDTO();

        vLanLinkDTO.setId( entity.getId() );
        vLanLinkDTO.setName( entity.getName() );

        return vLanLinkDTO;
    }

    @Override
    public List<VLanLink> toEntity(List<VLanLinkDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<VLanLink> list = new ArrayList<VLanLink>();
        for ( VLanLinkDTO vLanLinkDTO : dtoList ) {
            list.add( toEntity( vLanLinkDTO ) );
        }

        return list;
    }

    @Override
    public List<VLanLinkDTO> toDto(List<VLanLink> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VLanLinkDTO> list = new ArrayList<VLanLinkDTO>();
        for ( VLanLink vLanLink : entityList ) {
            list.add( toDto( vLanLink ) );
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
