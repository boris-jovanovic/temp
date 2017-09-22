package jhipster.monolithic.angular.service.mapper;

import jhipster.monolithic.angular.domain.*;
import jhipster.monolithic.angular.service.dto.RegionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegionMapper extends EntityMapper <RegionDTO, Region> {
    
    @Mapping(target = "regionToVLans", ignore = true)
    @Mapping(target = "regionToPools", ignore = true)
    Region toEntity(RegionDTO regionDTO); 
    default Region fromId(Long id) {
        if (id == null) {
            return null;
        }
        Region region = new Region();
        region.setId(id);
        return region;
    }
}
