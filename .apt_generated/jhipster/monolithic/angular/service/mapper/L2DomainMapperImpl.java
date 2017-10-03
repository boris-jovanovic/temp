package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;

import jhipster.monolithic.angular.domain.L2Domain;
import jhipster.monolithic.angular.service.dto.L2DomainDTO;
import org.springframework.stereotype.Component;

@Component
public class L2DomainMapperImpl implements L2DomainMapper {

    @Override
    public L2DomainDTO toDto(L2Domain l2Domain) {
        if ( l2Domain == null ) {
            return null;
        }

        L2DomainDTO l2DomainDTO = new L2DomainDTO();

        l2DomainDTO.setId( l2Domain.getId() );
        l2DomainDTO.setName( l2Domain.getName() );

        return l2DomainDTO;
    }

    @Override
    public List<L2DomainDTO> toDto(List<L2Domain> l2Domains) {
        if ( l2Domains == null ) {
            return null;
        }

        List<L2DomainDTO> result = new ArrayList<>();
        for ( L2Domain l2Domain : l2Domains ) {
            result.add( toDto( l2Domain ) );
        }

        return result;
    }

    @Override
    public List<L2Domain> toEntity(List<L2DomainDTO> l2DomainDTOs) {
        if ( l2DomainDTOs == null ) {
            return null;
        }

        List<L2Domain> result = new ArrayList<>();
        for ( L2DomainDTO l2DomainDTO : l2DomainDTOs ) {
            result.add( toEntity( l2DomainDTO ) );
        }

        return result;
    }

    @Override
    public L2Domain toEntity(L2DomainDTO l2DomainDTO) {
        if ( l2DomainDTO == null ) {
            return null;
        }

        L2Domain l2Domain = new L2Domain();

        l2Domain.setId( l2DomainDTO.getId() );
        l2Domain.setName( l2DomainDTO.getName() );

        return l2Domain;
    }
}
