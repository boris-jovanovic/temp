package jhipster.monolithic.angular.service;

import jhipster.monolithic.angular.service.dto.IpV4AddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing IpV4Address.
 */
public interface IpV4AddressService {

    /**
     * Save a ipV4Address.
     *
     * @param ipV4AddressDTO the entity to save
     * @return the persisted entity
     */
    IpV4AddressDTO save(IpV4AddressDTO ipV4AddressDTO);

    /**
     *  Get all the ipV4Addresses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<IpV4AddressDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" ipV4Address.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    IpV4AddressDTO findOne(Long id);

    /**
     *  Delete the "id" ipV4Address.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
