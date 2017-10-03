package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.IpPoolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Pool.
 */
public interface PoolService {

    /**
     * Save a pool.
     *
     * @param poolDTO the entity to save
     * @return the persisted entity
     */
    IpPoolDTO save(IpPoolDTO poolDTO);

    /**
     *  Get all the pools.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<IpPoolDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" pool.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    IpPoolDTO findOne(Long id);

    /**
     *  Delete the "id" pool.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
