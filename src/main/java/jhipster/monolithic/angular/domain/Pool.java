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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@NotNull
	@Column(name = "subnet", nullable = false)
	private String subnet;

	@OneToMany(mappedBy = "pool")
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<IpV4Address> ipV4Addresses = new HashSet<>();

	@ManyToOne
	private Region region;

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

	public Pool name(final String name) {
		this.name = name;
		return this;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<IpV4Address> getIpV4Addresses() {
		return ipV4Addresses;
	}

	public Pool ipv4Addresses(final Set<IpV4Address> ipV4Addresses) {
		this.ipV4Addresses = ipV4Addresses;
		return this;
	}

	public Pool addIpV4Address(final IpV4Address ipV4Address) {
		ipV4Addresses.add(ipV4Address);
		ipV4Address.setPool(this);
		return this;
	}

	public Pool removePool(final IpV4Address ipV4Address) {
		ipV4Addresses.remove(ipV4Address);
		ipV4Address.setPool(null);
		return this;
	}

	public void setPools(final Set<IpV4Address> ipV4Addresses) {
		this.ipV4Addresses = ipV4Addresses;
	}

	public Region getRegion() {
		return region;
	}

	public Pool region(final Region region) {
		this.region = region;
		return this;
	}

	public void setRegion(final Region region) {
		this.region = region;
	}
	// jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not
	// remove

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(final String subnet) {
		this.subnet = subnet;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Pool pool = (Pool) o;
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
		return "Pool{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
	}
}
