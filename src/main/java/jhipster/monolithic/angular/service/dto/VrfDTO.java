package jhipster.monolithic.angular.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Vrf entity.
 */
public class VrfDTO implements Serializable {

    private Long id;

    @NotNull
    private String vrfId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVrfId() {
        return vrfId;
    }

    public void setVrfId(String vrfId) {
        this.vrfId = vrfId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VrfDTO vrfDTO = (VrfDTO) o;
        if(vrfDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vrfDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VrfDTO{" +
            "id=" + getId() +
            ", vrfId='" + getVrfId() + "'" +
            "}";
    }
}
