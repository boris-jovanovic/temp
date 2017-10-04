package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;

/**
 * Mapper for the entity L2Domain and its DTO L2DomainDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VLanPoolMapper extends EntityMapper<VLanPoolDTO, VLanPool> {

	@Mapping(target = "VLans", ignore = true)
	VLanPool toEntity(VLanPoolDTO vLanPoolDTO);

	@Mapping(target = "VLans", ignore = true)
	VLanPoolDTO toDto(VLanPool vLanPool);

	default VLanPool fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final VLanPool vLanPool = new VLanPool();
		vLanPool.setId(id);
		return vLanPool;
	}
}
