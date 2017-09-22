package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import java.util.List;

/**
 * Service Interface for managing VLanLink.
 */
public interface VLanLinkService {

    /**
     * Save a vLanLink.
     *
     * @param vLanLinkDTO the entity to save
     * @return the persisted entity
     */
    VLanLinkDTO save(VLanLinkDTO vLanLinkDTO);

    /**
     *  Get all the vLanLinks.
     *
     *  @return the list of entities
     */
    List<VLanLinkDTO> findAll();

    /**
     *  Get the "id" vLanLink.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    VLanLinkDTO findOne(Long id);

    /**
     *  Delete the "id" vLanLink.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
