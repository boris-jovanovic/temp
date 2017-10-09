package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.IpPool;
import jhipster.monolithic.angular.domain.IpRegion;
import jhipster.monolithic.angular.domain.Vrf;
import jhipster.monolithic.angular.service.dto.IpPoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-06T16:28:01+0200",
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
        for ( IpPool ipPool : arg0 ) {
            list.add( toDto( ipPool ) );
        }

        return list;
    }

    @Override
    public List<IpPool> toEntity(List<IpPoolDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<IpPool> list = new ArrayList<IpPool>();
        for ( IpPoolDTO ipPoolDTO : arg0 ) {
            list.add( toEntity( ipPoolDTO ) );
        }

        return list;
    }

    @Override
    public IpPoolDTO toDto(IpPool pool) {
        if ( pool == null ) {
            return null;
        }

        IpPoolDTO ipPoolDTO_ = new IpPoolDTO();

        ipPoolDTO_.setVrfId( poolVrfId( pool ) );
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

        ipPool_.setVrf( vrfMapper.fromId( poolDTO.getVrfId() ) );
        ipPool_.setRegion( regionMapper.fromId( poolDTO.getRegionId() ) );
        ipPool_.setId( poolDTO.getId() );
        ipPool_.setName( poolDTO.getName() );
        ipPool_.setSubnet( poolDTO.getSubnet() );

        return ipPool_;
    }

    private Long poolVrfId(IpPool ipPool) {

        if ( ipPool == null ) {
            return null;
        }
        Vrf vrf = ipPool.getVrf();
        if ( vrf == null ) {
            return null;
        }
        Long id = vrf.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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
