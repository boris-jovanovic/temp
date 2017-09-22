package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.VrfDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Vrf.
 */
public interface VrfService {

    /**
     * Save a vrf.
     *
     * @param vrfDTO the entity to save
     * @return the persisted entity
     */
    VrfDTO save(VrfDTO vrfDTO);

    /**
     *  Get all the vrfs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<VrfDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" vrf.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    VrfDTO findOne(Long id);

    /**
     *  Delete the "id" vrf.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
