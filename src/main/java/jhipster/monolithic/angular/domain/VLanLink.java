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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	// @JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<VLan> vLanLinks = new HashSet<>();

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

	public VLanLink name(final String name) {
		this.name = name;
		return this;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<VLan> getVLanLinks() {
		return vLanLinks;
	}

	public VLanLink vLanLinks(final Set<VLan> vLans) {
		vLanLinks = vLans;
		return this;
	}

	public VLanLink addVLanLink(final VLan vLan) {
		vLanLinks.add(vLan);
		vLan.setVLanLink(this);
		return this;
	}

	public VLanLink removeVLanLink(final VLan vLan) {
		vLanLinks.remove(vLan);
		vLan.setVLanLink(null);
		return this;
	}

	public void setVLanLinks(final Set<VLan> vLans) {
		vLanLinks = vLans;
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
		final VLanLink vLanLink = (VLanLink) o;
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
		return "VLanLink{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
	}
}
