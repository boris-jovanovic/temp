package jhipster.monolithic.angular.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the VLan entity.
 */
public class VLanDTO implements Serializable {

	private Long id;

	@NotNull
	private String vLanId;

	private Long vrfId;

	private Long vLanLinkId;

	private Long regionId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getVLanId() {
		return vLanId;
	}

	public void setvLanId(final String vLanId) {
		this.vLanId = vLanId;
	}

	public Long getVrfId() {
		return vrfId;
	}

	public void setVrfId(final Long vrfId) {
		this.vrfId = vrfId;
	}

	public Long getVLanLinkId() {
		return vLanLinkId;
	}

	public void setVLanLinkId(final Long vLanLinkId) {
		this.vLanLinkId = vLanLinkId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(final Long regionId) {
		this.regionId = regionId;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final VLanDTO vLanDTO = (VLanDTO) o;
		if (vLanDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), vLanDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "VLanDTO{" + "id=" + getId() + ", vLanId='" + getVLanId() + "'" + "}";
	}
}
