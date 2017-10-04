package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.service.dto.VLanDTO;

/**
 * Mapper for the entity VLan and its DTO VLanDTO.
 */
@Mapper(componentModel = "spring", uses = { VrfMapper.class, VLanLinkMapper.class, VLanPoolMapper.class, })
public interface VLanMapper extends EntityMapper<VLanDTO, VLan> {

	@Mapping(source = "vrf.id", target = "vrfId")

	@Mapping(source = "VLanLink.id", target = "VLanLinkId")

	@Mapping(source = "VLanPool.id", target = "VLanPoolId")
	VLanDTO toDto(VLan vLan);

	@Mapping(source = "vrfId", target = "vrf")
	@Mapping(target = "VLans", ignore = true)

	@Mapping(source = "VLanLinkId", target = "VLanLink")

	@Mapping(source = "VLanPoolId", target = "VLanPool")
	VLan toEntity(VLanDTO vLanDTO);

	default VLan fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final VLan vLan = new VLan();
		vLan.setId(id);
		return vLan;
	}
}
