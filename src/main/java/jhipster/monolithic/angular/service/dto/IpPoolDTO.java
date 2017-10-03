package jhipster.monolithic.angular.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Pool entity.
 */
public class IpPoolDTO implements Serializable {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String subnet;

	private Long regionId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(final Long regionId) {
		this.regionId = regionId;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(final String subnet) {
		this.subnet = subnet;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final IpPoolDTO poolDTO = (IpPoolDTO) o;
		if (poolDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), poolDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "PoolDTO{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
	}
}
