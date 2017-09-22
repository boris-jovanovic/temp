package jhipster.monolithic.angular.repository;

import jhipster.monolithic.angular.domain.Pool;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Pool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoolRepository extends JpaRepository<Pool, Long> {

}
