package jhipster.monolithic.angular.service.mapper;

import jhipster.monolithic.angular.domain.*;
import jhipster.monolithic.angular.service.dto.PoolDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pool and its DTO PoolDTO.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class, })
public interface PoolMapper extends EntityMapper <PoolDTO, Pool> {

    @Mapping(source = "region.id", target = "regionId")
    PoolDTO toDto(Pool pool); 
    @Mapping(target = "pools", ignore = true)

    @Mapping(source = "regionId", target = "region")
    Pool toEntity(PoolDTO poolDTO); 
    default Pool fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pool pool = new Pool();
        pool.setId(id);
        return pool;
    }
}
