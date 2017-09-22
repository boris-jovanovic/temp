package jhipster.monolithic.angular.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the IpV4Address entity.
 */
public class IpV4AddressDTO implements Serializable {

    private Long id;

    @NotNull
    private String ipV4Address;

    private Long vLanId;

    private Long poolId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpV4Address() {
        return ipV4Address;
    }

    public void setIpV4Address(String ipV4Address) {
        this.ipV4Address = ipV4Address;
    }

    public Long getVLanId() {
        return vLanId;
    }

    public void setVLanId(Long vLanId) {
        this.vLanId = vLanId;
    }

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IpV4AddressDTO ipV4AddressDTO = (IpV4AddressDTO) o;
        if(ipV4AddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ipV4AddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IpV4AddressDTO{" +
            "id=" + getId() +
            ", ipV4Address='" + getIpV4Address() + "'" +
            "}";
    }
}
