package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;

/**
 * Mapper for the entity VLanLink and its DTO VLanLinkDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VLanLinkMapper extends EntityMapper<VLanLinkDTO, VLanLink> {

	@Mapping(target = "VLanLinks", ignore = true)
	VLanLink toEntity(VLanLinkDTO vLanLinkDTO);

	default VLanLink fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final VLanLink vLanLink = new VLanLink();
		vLanLink.setId(id);
		return vLanLink;
	}
}
