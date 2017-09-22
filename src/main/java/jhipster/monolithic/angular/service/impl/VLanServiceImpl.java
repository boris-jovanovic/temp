package jhipster.monolithic.angular.service.impl;

import jhipster.monolithic.angular.service.VLanService;
import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.repository.VLanRepository;
import jhipster.monolithic.angular.service.dto.VLanDTO;
import jhipster.monolithic.angular.service.mapper.VLanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing VLan.
 */
@Service
@Transactional
public class VLanServiceImpl implements VLanService{

    private final Logger log = LoggerFactory.getLogger(VLanServiceImpl.class);

    private final VLanRepository vLanRepository;

    private final VLanMapper vLanMapper;

    public VLanServiceImpl(VLanRepository vLanRepository, VLanMapper vLanMapper) {
        this.vLanRepository = vLanRepository;
        this.vLanMapper = vLanMapper;
    }

    /**
     * Save a vLan.
     *
     * @param vLanDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VLanDTO save(VLanDTO vLanDTO) {
        log.debug("Request to save VLan : {}", vLanDTO);
        VLan vLan = vLanMapper.toEntity(vLanDTO);
        vLan = vLanRepository.save(vLan);
        return vLanMapper.toDto(vLan);
    }

    /**
     *  Get all the vLans.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VLanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VLans");
        return vLanRepository.findAll(pageable)
            .map(vLanMapper::toDto);
    }

    /**
     *  Get one vLan by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VLanDTO findOne(Long id) {
        log.debug("Request to get VLan : {}", id);
        VLan vLan = vLanRepository.findOne(id);
        return vLanMapper.toDto(vLan);
    }

    /**
     *  Delete the  vLan by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VLan : {}", id);
        vLanRepository.delete(id);
    }
}
