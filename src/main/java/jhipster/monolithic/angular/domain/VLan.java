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
 * A VLan.
 */
@Entity
@Table(name = "v_lan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VLan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "v_lan_id", nullable = false)
    private String vLanId;

    @ManyToOne
    private Vrf vrf;

    @OneToMany(mappedBy = "vLan")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<IpV4Address> vLans = new HashSet<>();

    @ManyToOne
    private VLanLink vLanLink;

    @ManyToOne
    private Region region;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getvLanId() {
        return vLanId;
    }

    public VLan vLanId(String vLanId) {
        this.vLanId = vLanId;
        return this;
    }

    public void setvLanId(String vLanId) {
        this.vLanId = vLanId;
    }

    public Vrf getVrf() {
        return vrf;
    }

    public VLan vrf(Vrf vrf) {
        this.vrf = vrf;
        return this;
    }

    public void setVrf(Vrf vrf) {
        this.vrf = vrf;
    }

    public Set<IpV4Address> getVLans() {
        return vLans;
    }

    public VLan vLans(Set<IpV4Address> ipV4Addresses) {
        this.vLans = ipV4Addresses;
        return this;
    }

    public VLan addVLan(IpV4Address ipV4Address) {
        this.vLans.add(ipV4Address);
        ipV4Address.setVLan(this);
        return this;
    }

    public VLan removeVLan(IpV4Address ipV4Address) {
        this.vLans.remove(ipV4Address);
        ipV4Address.setVLan(null);
        return this;
    }

    public void setVLans(Set<IpV4Address> ipV4Addresses) {
        this.vLans = ipV4Addresses;
    }

    public VLanLink getVLanLink() {
        return vLanLink;
    }

    public VLan vLanLink(VLanLink vLanLink) {
        this.vLanLink = vLanLink;
        return this;
    }

    public void setVLanLink(VLanLink vLanLink) {
        this.vLanLink = vLanLink;
    }

    public Region getRegion() {
        return region;
    }

    public VLan region(Region region) {
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
        VLan vLan = (VLan) o;
        if (vLan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vLan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VLan{" +
            "id=" + getId() +
            ", vLanId='" + getvLanId() + "'" +
            "}";
    }
}
