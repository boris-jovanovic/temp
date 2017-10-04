package jhipster.monolithic.angular.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "l2_domain")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class L2Domain implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public L2Domain name(final String name) {
		this.name = name;
		return this;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
}
