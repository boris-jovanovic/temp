package jhipster.monolithic.angular.repository;

import jhipster.monolithic.angular.domain.IpRegion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Region entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegionRepository extends JpaRepository<IpRegion, Long> {

}
