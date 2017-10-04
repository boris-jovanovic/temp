package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.VLanPoolDTO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing L2Domain.
 */
public interface VLanPoolService {

    /**
     * Save a vLanPool.
     *
     * @param vLanPoolDTO the entity to save
     * @return the persisted entity
     */
	VLanPoolDTO save(VLanPoolDTO vLanPoolDTO);

    /**
     *  Get all the vLanPools.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
	Page<VLanPoolDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" vLanPool.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
	VLanPoolDTO findOne(Long id);

    /**
     *  Delete the "id" vLanPool.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
