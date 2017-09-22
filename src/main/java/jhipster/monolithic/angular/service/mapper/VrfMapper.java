package jhipster.monolithic.angular.service.mapper;

import jhipster.monolithic.angular.domain.*;
import jhipster.monolithic.angular.service.dto.VrfDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vrf and its DTO VrfDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VrfMapper extends EntityMapper <VrfDTO, Vrf> {
    
    @Mapping(target = "vrfs", ignore = true)
    Vrf toEntity(VrfDTO vrfDTO); 
    default Vrf fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vrf vrf = new Vrf();
        vrf.setId(id);
        return vrf;
    }
}
