package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;

/**
 * Mapper for the entity L2Domain and its DTO L2DomainDTO.
 */
@Mapper(componentModel = "spring", uses = { L2DomainMapper.class, VLanMapper.class })
public interface VLanPoolMapper extends EntityMapper<VLanPoolDTO, VLanPool> {

	@Mapping(source = "domainId", target = "domain")
	@Mapping(target = "VLans", source = "VLans")
	VLanPool toEntity(VLanPoolDTO vLanPoolDTO);

	@Mapping(source = "domain.id", target = "domainId")
	@Mapping(target = "VLans", source = "VLans")
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
