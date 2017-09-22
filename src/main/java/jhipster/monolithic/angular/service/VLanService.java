package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.VLanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing VLan.
 */
public interface VLanService {

    /**
     * Save a vLan.
     *
     * @param vLanDTO the entity to save
     * @return the persisted entity
     */
    VLanDTO save(VLanDTO vLanDTO);

    /**
     *  Get all the vLans.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<VLanDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" vLan.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    VLanDTO findOne(Long id);

    /**
     *  Delete the "id" vLan.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
