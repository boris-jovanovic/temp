package jhipster.monolithic.angular.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vlan_pool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VLanPool implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "vLanPool")
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<VLan> vLans = new HashSet<>();

	@ManyToOne
	private L2Domain domain;
	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public VLanPool name(final String name) {
		this.name = name;
		return this;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public Set<VLan> getVLans() {
		return vLans;
	}

	public VLanPool vLans(final Set<VLan> vLans) {
		this.vLans = vLans;
		return this;
	}

	public VLanPool addVLan(final VLan vLan) {
		vLans.add(vLan);
		vLan.setVLanPool(this);
		return this;
	}

	public VLanPool removeVLan(final VLan vLan) {
		vLans.remove(vLan);
		vLan.setVLanPool(null);
		return this;
	}

	public void setVLans(final Set<VLan> vLans) {
		this.vLans = vLans;
	}
	
	public L2Domain getDomain() {
		return domain;
	}

	public VLanPool domain(final L2Domain domain) {
		this.domain = domain;
		return this;
	}

	public void setDomain(final L2Domain domain) {
		this.domain = domain;
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VLanPool vLanPool = (VLanPool) o;
		if (vLanPool.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), vLanPool.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "VLanPool{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
	}
}
