package jhipster.monolithic.angular.service.impl;

import jhipster.monolithic.angular.service.IpV4AddressService;
import jhipster.monolithic.angular.domain.IpV4Address;
import jhipster.monolithic.angular.repository.IpV4AddressRepository;
import jhipster.monolithic.angular.service.dto.IpV4AddressDTO;
import jhipster.monolithic.angular.service.mapper.IpV4AddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing IpV4Address.
 */
@Service
@Transactional
public class IpV4AddressServiceImpl implements IpV4AddressService{

    private final Logger log = LoggerFactory.getLogger(IpV4AddressServiceImpl.class);

    private final IpV4AddressRepository ipV4AddressRepository;

    private final IpV4AddressMapper ipV4AddressMapper;

    public IpV4AddressServiceImpl(IpV4AddressRepository ipV4AddressRepository, IpV4AddressMapper ipV4AddressMapper) {
        this.ipV4AddressRepository = ipV4AddressRepository;
        this.ipV4AddressMapper = ipV4AddressMapper;
    }

    /**
     * Save a ipV4Address.
     *
     * @param ipV4AddressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IpV4AddressDTO save(IpV4AddressDTO ipV4AddressDTO) {
        log.debug("Request to save IpV4Address : {}", ipV4AddressDTO);
        IpV4Address ipV4Address = ipV4AddressMapper.toEntity(ipV4AddressDTO);
        ipV4Address = ipV4AddressRepository.save(ipV4Address);
        return ipV4AddressMapper.toDto(ipV4Address);
    }

    /**
     *  Get all the ipV4Addresses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IpV4AddressDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IpV4Addresses");
        return ipV4AddressRepository.findAll(pageable)
            .map(ipV4AddressMapper::toDto);
    }

    /**
     *  Get one ipV4Address by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public IpV4AddressDTO findOne(Long id) {
        log.debug("Request to get IpV4Address : {}", id);
        IpV4Address ipV4Address = ipV4AddressRepository.findOne(id);
        return ipV4AddressMapper.toDto(ipV4Address);
    }

    /**
     *  Delete the  ipV4Address by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IpV4Address : {}", id);
        ipV4AddressRepository.delete(id);
    }
}
