package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.L2DomainDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing L2Domain.
 */
public interface L2DomainService {

    /**
     * Save a l2Domain.
     *
     * @param l2DomainDTO the entity to save
     * @return the persisted entity
     */
	L2DomainDTO save(L2DomainDTO l2DomainDTO);

    /**
     *  Get all the l2Domains.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
	Page<L2DomainDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" l2Domain.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    L2DomainDTO findOne(Long id);

    /**
     *  Delete the "id" l2Domain.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
