package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.Vrf;
import jhipster.monolithic.angular.service.dto.VrfDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-09-26T10:35:16+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class VrfMapperImpl implements VrfMapper {

    @Override
    public VrfDTO toDto(Vrf entity) {
        if ( entity == null ) {
            return null;
        }

        VrfDTO vrfDTO = new VrfDTO();

        vrfDTO.setId( entity.getId() );
        vrfDTO.setVrfId( entity.getVrfId() );

        return vrfDTO;
    }

    @Override
    public List<Vrf> toEntity(List<VrfDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Vrf> list = new ArrayList<Vrf>();
        for ( VrfDTO vrfDTO : dtoList ) {
            list.add( toEntity( vrfDTO ) );
        }

        return list;
    }

    @Override
    public List<VrfDTO> toDto(List<Vrf> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VrfDTO> list = new ArrayList<VrfDTO>();
        for ( Vrf vrf : entityList ) {
            list.add( toDto( vrf ) );
        }

        return list;
    }

    @Override
    public Vrf toEntity(VrfDTO vrfDTO) {
        if ( vrfDTO == null ) {
            return null;
        }

        Vrf vrf_ = new Vrf();

        vrf_.setId( vrfDTO.getId() );
        vrf_.setVrfId( vrfDTO.getVrfId() );

        return vrf_;
    }
}
