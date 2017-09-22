package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.Pool;
import jhipster.monolithic.angular.domain.Region;
import jhipster.monolithic.angular.service.dto.PoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-09-21T15:32:16+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class PoolMapperImpl implements PoolMapper {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Pool> toEntity(List<PoolDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Pool> list = new ArrayList<Pool>();
        for ( PoolDTO poolDTO : dtoList ) {
            list.add( toEntity( poolDTO ) );
        }

        return list;
    }

    @Override
    public List<PoolDTO> toDto(List<Pool> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PoolDTO> list = new ArrayList<PoolDTO>();
        for ( Pool pool : entityList ) {
            list.add( toDto( pool ) );
        }

        return list;
    }

    @Override
    public PoolDTO toDto(Pool pool) {
        if ( pool == null ) {
            return null;
        }

        PoolDTO poolDTO_ = new PoolDTO();

        poolDTO_.setRegionId( poolRegionId( pool ) );
        poolDTO_.setId( pool.getId() );
        poolDTO_.setName( pool.getName() );

        return poolDTO_;
    }

    @Override
    public Pool toEntity(PoolDTO poolDTO) {
        if ( poolDTO == null ) {
            return null;
        }

        Pool pool_ = new Pool();

        pool_.setRegion( regionMapper.fromId( poolDTO.getRegionId() ) );
        pool_.setId( poolDTO.getId() );
        pool_.setName( poolDTO.getName() );

        return pool_;
    }

    private Long poolRegionId(Pool pool) {

        if ( pool == null ) {
            return null;
        }
        Region region = pool.getRegion();
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
