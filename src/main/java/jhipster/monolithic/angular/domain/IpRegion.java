package jhipster.monolithic.angular.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Region.
 */
@Entity
@Table(name = "ip_region")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IpRegion implements Serializable {

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
	private Set<IpPool> pools = new HashSet<>();

	// jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public IpRegion name(final String name) {
		this.name = name;
		return this;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<IpPool> gePools() {
		return pools;
	}

	public IpRegion pools(final Set<IpPool> pools) {
		this.pools = pools;
		return this;
	}

	public IpRegion addPool(final IpPool pool) {
		pools.add(pool);
		pool.setRegion(this);
		return this;
	}

	public IpRegion removePool(final IpPool pool) {
		pools.remove(pool);
		pool.setRegion(null);
		return this;
	}

	public void setPools(final Set<IpPool> pools) {
		this.pools = pools;
	}
	// jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not
	// remove

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final IpRegion region = (IpRegion) o;
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
		return "Region{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
	}
}
