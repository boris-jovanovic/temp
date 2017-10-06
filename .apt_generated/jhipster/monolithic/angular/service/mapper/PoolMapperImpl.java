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
    date = "2017-09-26T15:09:15+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class PoolMapperImpl implements PoolMapper {

    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private VrfMapper vrfMapper;

    @Override
    public List<IpPoolDTO> toDto(List<IpPool> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IpPoolDTO> list = new ArrayList<IpPoolDTO>();
        for ( IpPool pool : arg0 ) {
            list.add( toDto( pool ) );
        }

        return list;
    }

    @Override
    public List<IpPool> toEntity(List<IpPoolDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IpPool> list = new ArrayList<IpPool>();
        for ( IpPoolDTO poolDTO : arg0 ) {
            list.add( toEntity( poolDTO ) );
        }

        return list;
    }

    @Override
    public IpPoolDTO toDto(IpPool pool) {
        if ( pool == null ) {
            return null;
        }

        IpPoolDTO poolDTO_ = new IpPoolDTO();

        poolDTO_.setRegionId( poolRegionId( pool ) );
        poolDTO_.setId( pool.getId() );
        poolDTO_.setName( pool.getName() );
        poolDTO_.setSubnet( pool.getSubnet() );
        poolDTO_.setVrfId(pool.getVrf().getId());

        return poolDTO_;
    }

    @Override
    public IpPool toEntity(IpPoolDTO poolDTO) {
        if ( poolDTO == null ) {
            return null;
        }

        IpPool pool_ = new IpPool();

        pool_.setRegion( regionMapper.fromId( poolDTO.getRegionId() ) );
        pool_.setId( poolDTO.getId() );
        pool_.setName( poolDTO.getName() );
        pool_.setSubnet( poolDTO.getSubnet() );
        pool_.setVrf( vrfMapper.fromId( poolDTO.getVrfId() ) );

        return pool_;
    }

    private Long poolRegionId(IpPool pool) {

        if ( pool == null ) {
            return null;
        }
        IpRegion region = pool.getRegion();
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
