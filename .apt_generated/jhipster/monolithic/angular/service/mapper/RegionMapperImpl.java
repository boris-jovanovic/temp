package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.IpRegion;
import jhipster.monolithic.angular.service.dto.RegionDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-09-26T15:06:52+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public RegionDTO toDto(IpRegion arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RegionDTO regionDTO = new RegionDTO();

        regionDTO.setId( arg0.getId() );
        regionDTO.setName( arg0.getName() );

        return regionDTO;
    }

    @Override
    public List<RegionDTO> toDto(List<IpRegion> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RegionDTO> list = new ArrayList<RegionDTO>();
        for ( IpRegion region : arg0 ) {
            list.add( toDto( region ) );
        }

        return list;
    }

    @Override
    public List<IpRegion> toEntity(List<RegionDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IpRegion> list = new ArrayList<IpRegion>();
        for ( RegionDTO regionDTO : arg0 ) {
            list.add( toEntity( regionDTO ) );
        }

        return list;
    }

    @Override
    public IpRegion toEntity(RegionDTO regionDTO) {
        if ( regionDTO == null ) {
            return null;
        }

        IpRegion region_ = new IpRegion();

        region_.setId( regionDTO.getId() );
        region_.setName( regionDTO.getName() );

        return region_;
    }
}
