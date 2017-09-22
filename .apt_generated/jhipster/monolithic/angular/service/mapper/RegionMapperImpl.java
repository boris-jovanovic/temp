package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.Region;
import jhipster.monolithic.angular.service.dto.RegionDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-09-21T15:32:16+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public RegionDTO toDto(Region entity) {
        if ( entity == null ) {
            return null;
        }

        RegionDTO regionDTO = new RegionDTO();

        regionDTO.setId( entity.getId() );
        regionDTO.setName( entity.getName() );

        return regionDTO;
    }

    @Override
    public List<Region> toEntity(List<RegionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Region> list = new ArrayList<Region>();
        for ( RegionDTO regionDTO : dtoList ) {
            list.add( toEntity( regionDTO ) );
        }

        return list;
    }

    @Override
    public List<RegionDTO> toDto(List<Region> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RegionDTO> list = new ArrayList<RegionDTO>();
        for ( Region region : entityList ) {
            list.add( toDto( region ) );
        }

        return list;
    }

    @Override
    public Region toEntity(RegionDTO regionDTO) {
        if ( regionDTO == null ) {
            return null;
        }

        Region region_ = new Region();

        region_.setId( regionDTO.getId() );
        region_.setName( regionDTO.getName() );

        return region_;
    }
}
