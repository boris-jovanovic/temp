package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.L2Domain;
import jhipster.monolithic.angular.service.dto.L2DomainDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-04T14:33:01+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class L2DomainMapperImpl implements L2DomainMapper {

    @Override
    public L2Domain toEntity(L2DomainDTO dto) {
        if ( dto == null ) {
            return null;
        }

        L2Domain l2Domain = new L2Domain();

        l2Domain.setId( dto.getId() );
        l2Domain.setName( dto.getName() );

        return l2Domain;
    }

    @Override
    public L2DomainDTO toDto(L2Domain entity) {
        if ( entity == null ) {
            return null;
        }

        L2DomainDTO l2DomainDTO = new L2DomainDTO();

        l2DomainDTO.setId( entity.getId() );
        l2DomainDTO.setName( entity.getName() );

        return l2DomainDTO;
    }

    @Override
    public List<L2Domain> toEntity(List<L2DomainDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<L2Domain> list = new ArrayList<L2Domain>();
        for ( L2DomainDTO l2DomainDTO : dtoList ) {
            list.add( toEntity( l2DomainDTO ) );
        }

        return list;
    }

    @Override
    public List<L2DomainDTO> toDto(List<L2Domain> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<L2DomainDTO> list = new ArrayList<L2DomainDTO>();
        for ( L2Domain l2Domain : entityList ) {
            list.add( toDto( l2Domain ) );
        }

        return list;
    }
}
