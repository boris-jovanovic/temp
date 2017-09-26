package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.Region;
import jhipster.monolithic.angular.service.dto.RegionDTO;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegionMapper extends EntityMapper<RegionDTO, Region> {

	@Mapping(target = "VLans", ignore = true)
	@Mapping(target = "pools", ignore = true)
	Region toEntity(RegionDTO regionDTO);

	default Region fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final Region region = new Region();
		region.setId(id);
		return region;
	}
}
