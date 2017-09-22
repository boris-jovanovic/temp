package jhipster.monolithic.angular.repository;

import jhipster.monolithic.angular.domain.IpV4Address;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the IpV4Address entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IpV4AddressRepository extends JpaRepository<IpV4Address, Long> {

}
