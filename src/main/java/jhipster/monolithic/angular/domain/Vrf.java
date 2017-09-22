package jhipster.monolithic.angular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Vrf.
 */
@Entity
@Table(name = "vrf")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vrf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "vrf_id", nullable = false)
    private String vrfId;

    @OneToMany(mappedBy = "vrf")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VLan> vrfs = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVrfId() {
        return vrfId;
    }

    public Vrf vrfId(String vrfId) {
        this.vrfId = vrfId;
        return this;
    }

    public void setVrfId(String vrfId) {
        this.vrfId = vrfId;
    }

    public Set<VLan> getVrfs() {
        return vrfs;
    }

    public Vrf vrfs(Set<VLan> vLans) {
        this.vrfs = vLans;
        return this;
    }

    public Vrf addVrf(VLan vLan) {
        this.vrfs.add(vLan);
        vLan.setVrf(this);
        return this;
    }

    public Vrf removeVrf(VLan vLan) {
        this.vrfs.remove(vLan);
        vLan.setVrf(null);
        return this;
    }

    public void setVrfs(Set<VLan> vLans) {
        this.vrfs = vLans;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vrf vrf = (Vrf) o;
        if (vrf.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vrf.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vrf{" +
            "id=" + getId() +
            ", vrfId='" + getVrfId() + "'" +
            "}";
    }
}
