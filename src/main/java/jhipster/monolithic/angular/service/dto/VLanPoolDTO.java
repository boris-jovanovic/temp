package jhipster.monolithic.angular.service.dto;

import java.io.Serializable;
import java.util.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A DTO for the VLan entity.
 */
public class VLanPoolDTO implements Serializable {

	private Long id;

	@NotNull
	private String name;

	private List<VLanDTO> vLans = new ArrayList<>();
	
	private Long domainId;

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
	
	public List<VLanDTO> getVLans() {
		return vLans;
	}

	@JsonProperty("vLans")
	public void setVLans(final List<VLanDTO> vLans) {
		this.vLans = vLans;
	}
	
	public Long getDomainId() {
		return domainId;
	}
	
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final VLanPoolDTO l2DomainDTO = (VLanPoolDTO) o;
		if (l2DomainDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), l2DomainDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "VLanDTO{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
	}
}
