package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.IpRegion;
import jhipster.monolithic.angular.service.dto.RegionDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-04T14:33:01+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public RegionDTO toDto(IpRegion entity) {
        if ( entity == null ) {
            return null;
        }

        RegionDTO regionDTO = new RegionDTO();

        regionDTO.setId( entity.getId() );
        regionDTO.setName( entity.getName() );

        return regionDTO;
    }

    @Override
    public List<IpRegion> toEntity(List<RegionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<IpRegion> list = new ArrayList<IpRegion>();
        for ( RegionDTO regionDTO : dtoList ) {
            list.add( toEntity( regionDTO ) );
        }

        return list;
    }

    @Override
    public List<RegionDTO> toDto(List<IpRegion> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RegionDTO> list = new ArrayList<RegionDTO>();
        for ( IpRegion ipRegion : entityList ) {
            list.add( toDto( ipRegion ) );
        }

        return list;
    }

    @Override
    public IpRegion toEntity(RegionDTO regionDTO) {
        if ( regionDTO == null ) {
            return null;
        }

        IpRegion ipRegion_ = new IpRegion();

        ipRegion_.setId( regionDTO.getId() );
        ipRegion_.setName( regionDTO.getName() );

        return ipRegion_;
    }
}
