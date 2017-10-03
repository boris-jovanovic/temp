package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.L2Domain;
import jhipster.monolithic.angular.service.dto.L2DomainDTO;

/**
 * Mapper for the entity L2Domain and its DTO L2DomainDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface L2DomainMapper extends EntityMapper<L2DomainDTO, L2Domain> {

	@Mapping(target = "L2Domains", ignore = true)
	L2Domain toEntity(L2DomainDTO l2DomainDTO);

	default L2Domain fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final L2Domain l2Domain = new L2Domain();
		l2Domain.setId(id);
		return l2Domain;
	}
}
