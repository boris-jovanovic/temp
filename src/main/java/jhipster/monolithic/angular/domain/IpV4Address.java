package jhipster.monolithic.angular.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A IpV4Address.
 */
@Entity
@Table(name = "ip_v_4_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IpV4Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ip_v_4_address", nullable = false)
    private String ipV4Address;

    @ManyToOne
    private VLan vLan;

    @ManyToOne
    private Pool pool;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpV4Address() {
        return ipV4Address;
    }

    public IpV4Address ipV4Address(String ipV4Address) {
        this.ipV4Address = ipV4Address;
        return this;
    }

    public void setIpV4Address(String ipV4Address) {
        this.ipV4Address = ipV4Address;
    }

    public VLan getVLan() {
        return vLan;
    }

    public IpV4Address vLan(VLan vLan) {
        this.vLan = vLan;
        return this;
    }

    public void setVLan(VLan vLan) {
        this.vLan = vLan;
    }

    public Pool getPool() {
        return pool;
    }

    public IpV4Address pool(Pool pool) {
        this.pool = pool;
        return this;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
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
        IpV4Address ipV4Address = (IpV4Address) o;
        if (ipV4Address.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ipV4Address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IpV4Address{" +
            "id=" + getId() +
            ", ipV4Address='" + getIpV4Address() + "'" +
            "}";
    }
}
