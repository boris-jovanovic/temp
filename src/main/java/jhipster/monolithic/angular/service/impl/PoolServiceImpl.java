package jhipster.monolithic.angular.service.impl;

import jhipster.monolithic.angular.service.PoolService;
import jhipster.monolithic.angular.domain.IpPool;
import jhipster.monolithic.angular.repository.PoolRepository;
import jhipster.monolithic.angular.service.dto.IpPoolDTO;
import jhipster.monolithic.angular.service.mapper.PoolMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Pool.
 */
@Service
@Transactional
public class PoolServiceImpl implements PoolService{

    private final Logger log = LoggerFactory.getLogger(PoolServiceImpl.class);

    private final PoolRepository poolRepository;

    private final PoolMapper poolMapper;

    public PoolServiceImpl(PoolRepository poolRepository, PoolMapper poolMapper) {
        this.poolRepository = poolRepository;
        this.poolMapper = poolMapper;
    }

    /**
     * Save a pool.
     *
     * @param poolDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IpPoolDTO save(IpPoolDTO poolDTO) {
        log.debug("Request to save Pool : {}", poolDTO);
        IpPool pool = poolMapper.toEntity(poolDTO);
        pool = poolRepository.save(pool);
        return poolMapper.toDto(pool);
    }

    /**
     *  Get all the pools.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IpPoolDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pools");
        return poolRepository.findAll(pageable)
            .map(poolMapper::toDto);
    }

    /**
     *  Get one pool by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public IpPoolDTO findOne(Long id) {
        log.debug("Request to get Pool : {}", id);
        IpPool pool = poolRepository.findOne(id);
        return poolMapper.toDto(pool);
    }

    /**
     *  Delete the  pool by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pool : {}", id);
        poolRepository.delete(id);
    }
}
