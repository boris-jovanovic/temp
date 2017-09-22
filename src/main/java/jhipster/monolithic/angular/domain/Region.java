package jhipster.monolithic.angular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Region.
 */
@Entity
@Table(name = "region")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "region")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VLan> regionToVLans = new HashSet<>();

    @OneToMany(mappedBy = "region")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pool> regionToPools = new HashSet<>();

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

    public Region name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VLan> getRegionToVLans() {
        return regionToVLans;
    }

    public Region regionToVLans(Set<VLan> vLans) {
        this.regionToVLans = vLans;
        return this;
    }

    public Region addRegionToVLan(VLan vLan) {
        this.regionToVLans.add(vLan);
        vLan.setRegion(this);
        return this;
    }

    public Region removeRegionToVLan(VLan vLan) {
        this.regionToVLans.remove(vLan);
        vLan.setRegion(null);
        return this;
    }

    public void setRegionToVLans(Set<VLan> vLans) {
        this.regionToVLans = vLans;
    }

    public Set<Pool> getRegionToPools() {
        return regionToPools;
    }

    public Region regionToPools(Set<Pool> pools) {
        this.regionToPools = pools;
        return this;
    }

    public Region addRegionToPool(Pool pool) {
        this.regionToPools.add(pool);
        pool.setRegion(this);
        return this;
    }

    public Region removeRegionToPool(Pool pool) {
        this.regionToPools.remove(pool);
        pool.setRegion(null);
        return this;
    }

    public void setRegionToPools(Set<Pool> pools) {
        this.regionToPools = pools;
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
        Region region = (Region) o;
        if (region.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), region.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Region{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
