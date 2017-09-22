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
 * A VLanLink.
 */
@Entity
@Table(name = "v_lan_link")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VLanLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "vLanLink")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VLan> vLanLinks = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public VLanLink name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VLan> getVLanLinks() {
        return vLanLinks;
    }

    public VLanLink vLanLinks(Set<VLan> vLans) {
        this.vLanLinks = vLans;
        return this;
    }

    public VLanLink addVLanLink(VLan vLan) {
        this.vLanLinks.add(vLan);
        vLan.setVLanLink(this);
        return this;
    }

    public VLanLink removeVLanLink(VLan vLan) {
        this.vLanLinks.remove(vLan);
        vLan.setVLanLink(null);
        return this;
    }

    public void setVLanLinks(Set<VLan> vLans) {
        this.vLanLinks = vLans;
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
        VLanLink vLanLink = (VLanLink) o;
        if (vLanLink.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vLanLink.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VLanLink{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
