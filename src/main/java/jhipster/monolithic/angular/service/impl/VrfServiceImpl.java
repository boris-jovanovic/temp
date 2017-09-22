package jhipster.monolithic.angular.service.impl;

import jhipster.monolithic.angular.service.VrfService;
import jhipster.monolithic.angular.domain.Vrf;
import jhipster.monolithic.angular.repository.VrfRepository;
import jhipster.monolithic.angular.service.dto.VrfDTO;
import jhipster.monolithic.angular.service.mapper.VrfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Vrf.
 */
@Service
@Transactional
public class VrfServiceImpl implements VrfService{

    private final Logger log = LoggerFactory.getLogger(VrfServiceImpl.class);

    private final VrfRepository vrfRepository;

    private final VrfMapper vrfMapper;

    public VrfServiceImpl(VrfRepository vrfRepository, VrfMapper vrfMapper) {
        this.vrfRepository = vrfRepository;
        this.vrfMapper = vrfMapper;
    }

    /**
     * Save a vrf.
     *
     * @param vrfDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VrfDTO save(VrfDTO vrfDTO) {
        log.debug("Request to save Vrf : {}", vrfDTO);
        Vrf vrf = vrfMapper.toEntity(vrfDTO);
        vrf = vrfRepository.save(vrf);
        return vrfMapper.toDto(vrf);
    }

    /**
     *  Get all the vrfs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VrfDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vrfs");
        return vrfRepository.findAll(pageable)
            .map(vrfMapper::toDto);
    }

    /**
     *  Get one vrf by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VrfDTO findOne(Long id) {
        log.debug("Request to get Vrf : {}", id);
        Vrf vrf = vrfRepository.findOne(id);
        return vrfMapper.toDto(vrf);
    }

    /**
     *  Delete the  vrf by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vrf : {}", id);
        vrfRepository.delete(id);
    }
}
