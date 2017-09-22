package jhipster.monolithic.angular.repository;

import jhipster.monolithic.angular.domain.VLanLink;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the VLanLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VLanLinkRepository extends JpaRepository<VLanLink, Long> {

}
