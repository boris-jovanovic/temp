package jhipster.monolithic.angular.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Pool entity.
 */
public class PoolDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long regionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PoolDTO poolDTO = (PoolDTO) o;
        if(poolDTO.getId() == null || getId() == null) {
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
        return "PoolDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
