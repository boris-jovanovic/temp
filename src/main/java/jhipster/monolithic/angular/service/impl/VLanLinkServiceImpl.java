package jhipster.monolithic.angular.service.impl;

import jhipster.monolithic.angular.service.VLanLinkService;
import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.repository.VLanLinkRepository;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import jhipster.monolithic.angular.service.mapper.VLanLinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing VLanLink.
 */
@Service
@Transactional
public class VLanLinkServiceImpl implements VLanLinkService{

    private final Logger log = LoggerFactory.getLogger(VLanLinkServiceImpl.class);

    private final VLanLinkRepository vLanLinkRepository;

    private final VLanLinkMapper vLanLinkMapper;

    public VLanLinkServiceImpl(VLanLinkRepository vLanLinkRepository, VLanLinkMapper vLanLinkMapper) {
        this.vLanLinkRepository = vLanLinkRepository;
        this.vLanLinkMapper = vLanLinkMapper;
    }

    /**
     * Save a vLanLink.
     *
     * @param vLanLinkDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VLanLinkDTO save(VLanLinkDTO vLanLinkDTO) {
        log.debug("Request to save VLanLink : {}", vLanLinkDTO);
        VLanLink vLanLink = vLanLinkMapper.toEntity(vLanLinkDTO);
        vLanLink = vLanLinkRepository.save(vLanLink);
        return vLanLinkMapper.toDto(vLanLink);
    }

    /**
     *  Get all the vLanLinks.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VLanLinkDTO> findAll() {
        log.debug("Request to get all VLanLinks");
        return vLanLinkRepository.findAll().stream()
            .map(vLanLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one vLanLink by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VLanLinkDTO findOne(Long id) {
        log.debug("Request to get VLanLink : {}", id);
        VLanLink vLanLink = vLanLinkRepository.findOne(id);
        return vLanLinkMapper.toDto(vLanLink);
    }

    /**
     *  Delete the  vLanLink by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VLanLink : {}", id);
        vLanLinkRepository.delete(id);
    }
}
