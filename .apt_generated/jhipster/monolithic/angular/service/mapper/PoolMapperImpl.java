package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.IpPool;
import jhipster.monolithic.angular.domain.IpRegion;
import jhipster.monolithic.angular.service.dto.IpPoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-04T14:33:01+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class PoolMapperImpl implements PoolMapper {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<IpPool> toEntity(List<IpPoolDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<IpPool> list = new ArrayList<IpPool>();
        for ( IpPoolDTO ipPoolDTO : dtoList ) {
            list.add( toEntity( ipPoolDTO ) );
        }

        return list;
    }

    @Override
    public List<IpPoolDTO> toDto(List<IpPool> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IpPoolDTO> list = new ArrayList<IpPoolDTO>();
        for ( IpPool ipPool : entityList ) {
            list.add( toDto( ipPool ) );
        }

        return list;
    }

    @Override
    public IpPoolDTO toDto(IpPool pool) {
        if ( pool == null ) {
            return null;
        }

        IpPoolDTO ipPoolDTO_ = new IpPoolDTO();

        ipPoolDTO_.setRegionId( poolRegionId( pool ) );
        ipPoolDTO_.setId( pool.getId() );
        ipPoolDTO_.setName( pool.getName() );
        ipPoolDTO_.setSubnet( pool.getSubnet() );

        return ipPoolDTO_;
    }

    @Override
    public IpPool toEntity(IpPoolDTO poolDTO) {
        if ( poolDTO == null ) {
            return null;
        }

        IpPool ipPool_ = new IpPool();

        ipPool_.setRegion( regionMapper.fromId( poolDTO.getRegionId() ) );
        ipPool_.setId( poolDTO.getId() );
        ipPool_.setName( poolDTO.getName() );
        ipPool_.setSubnet( poolDTO.getSubnet() );

        return ipPool_;
    }

    private Long poolRegionId(IpPool ipPool) {

        if ( ipPool == null ) {
            return null;
        }
        IpRegion region = ipPool.getRegion();
        if ( region == null ) {
            return null;
        }
        Long id = region.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
