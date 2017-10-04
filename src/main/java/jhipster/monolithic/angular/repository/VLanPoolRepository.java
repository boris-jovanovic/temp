package jhipster.monolithic.angular.repository;

import org.springframework.stereotype.Repository;

import jhipster.monolithic.angular.domain.*;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the VLanLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VLanPoolRepository extends JpaRepository<VLanPool, Long> {

}
