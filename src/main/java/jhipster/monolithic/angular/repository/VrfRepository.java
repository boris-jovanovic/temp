package jhipster.monolithic.angular.repository;

import jhipster.monolithic.angular.domain.Vrf;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Vrf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VrfRepository extends JpaRepository<Vrf, Long> {

}
