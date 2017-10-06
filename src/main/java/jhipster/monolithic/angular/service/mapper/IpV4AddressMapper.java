package jhipster.monolithic.angular.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import jhipster.monolithic.angular.domain.IpV4Address;
import jhipster.monolithic.angular.service.dto.IpV4AddressDTO;

/**
 * Mapper for the entity IpV4Address and its DTO IpV4AddressDTO.
 */
@Mapper(componentModel = "spring", uses = { VLanMapper.class, PoolMapper.class, })
public interface IpV4AddressMapper extends EntityMapper<IpV4AddressDTO, IpV4Address> {

	@Mapping(source = "VLan.id", target = "VLanId")
	@Mapping(source = "pool.id", target = "poolId")
	IpV4AddressDTO toDto(IpV4Address ipV4Address);

	@Mapping(source = "VLanId", target = "VLan")
	@Mapping(source = "poolId", target = "pool")
	IpV4Address toEntity(IpV4AddressDTO ipV4AddressDTO);

	default IpV4Address fromId(final Long id) {
		if (id == null) {
			return null;
		}
		final IpV4Address ipV4Address = new IpV4Address();
		ipV4Address.setId(id);
		return ipV4Address;
	}
}
