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
 * A Pool.
 */
@Entity
@Table(name = "pool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pool implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "pool")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<IpV4Address> pools = new HashSet<>();

    @ManyToOne
    private Region region;

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

    public Pool name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<IpV4Address> getPools() {
        return pools;
    }

    public Pool pools(Set<IpV4Address> ipV4Addresses) {
        this.pools = ipV4Addresses;
        return this;
    }

    public Pool addPool(IpV4Address ipV4Address) {
        this.pools.add(ipV4Address);
        ipV4Address.setPool(this);
        return this;
    }

    public Pool removePool(IpV4Address ipV4Address) {
        this.pools.remove(ipV4Address);
        ipV4Address.setPool(null);
        return this;
    }

    public void setPools(Set<IpV4Address> ipV4Addresses) {
        this.pools = ipV4Addresses;
    }

    public Region getRegion() {
        return region;
    }

    public Pool region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
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
        Pool pool = (Pool) o;
        if (pool.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pool.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pool{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
