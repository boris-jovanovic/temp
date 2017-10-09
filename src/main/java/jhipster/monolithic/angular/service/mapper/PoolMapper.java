package jhipster.monolithic.angular.service.mapper;

import jhipster.monolithic.angular.domain.*;
import jhipster.monolithic.angular.service.dto.IpPoolDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pool and its DTO PoolDTO.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class, VrfMapper.class })
public interface PoolMapper extends EntityMapper <IpPoolDTO, IpPool> {

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "vrf.id", target = "vrfId")
    IpPoolDTO toDto(IpPool pool); 
    @Mapping(target = "pools", ignore = true)

    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "vrfId", target = "vrf")
    IpPool toEntity(IpPoolDTO poolDTO); 
    default IpPool fromId(Long id) {
        if (id == null) {
            return null;
        }
        IpPool pool = new IpPool();
        pool.setId(id);
        return pool;
    }
}
