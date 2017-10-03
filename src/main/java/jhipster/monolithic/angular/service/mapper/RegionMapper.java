package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.IpRegion;
import jhipster.monolithic.angular.service.dto.RegionDTO;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegionMapper extends EntityMapper<RegionDTO, IpRegion> {

	@Mapping(target = "VLans", ignore = true)
	@Mapping(target = "pools", ignore = true)
	IpRegion toEntity(RegionDTO regionDTO);

	default IpRegion fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final IpRegion region = new IpRegion();
		region.setId(id);
		return region;
	}
}
