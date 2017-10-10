package jhipster.monolithic.angular.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.repository.VLanPoolRepository;
import jhipster.monolithic.angular.service.VLanPoolService;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;
import jhipster.monolithic.angular.service.mapper.VLanPoolMapper;

/**
 * Service Implementation for managing VLanLink.
 */
@Service
@Transactional
public class VLanPoolServiceImpl implements VLanPoolService {

	private final Logger log = LoggerFactory.getLogger(VLanPoolServiceImpl.class);

	private final VLanPoolMapper vLanPoolMapper;

	private final VLanPoolRepository vLanPoolRepository;

	public VLanPoolServiceImpl(final VLanPoolMapper vLanPoolMapper, final VLanPoolRepository vLanPoolRepository) {
		this.vLanPoolMapper = vLanPoolMapper;
		this.vLanPoolRepository = vLanPoolRepository;
	}

	/**
	 * Save a vLanLink.
	 *
	 * @param vLanPoolDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public VLanPoolDTO save(final VLanPoolDTO vLanPoolDTO) {
		log.debug("Request to save VLanPoolLink : {}", vLanPoolDTO);

		VLanPool vLanLink = vLanPoolMapper.toEntity(vLanPoolDTO);
		vLanLink = vLanPoolRepository.save(vLanLink);
		return vLanPoolMapper.toDto(vLanLink);
	}

	/**
	 * Get all the vLanLinks.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<VLanPoolDTO> findAll(final Pageable pageable) {
		log.debug("Request to get all VLanLinks");
		return vLanPoolRepository.findAll(pageable).map(vLanPoolMapper::toDto);
	}

	/**
	 * Get one vLanLink by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public VLanPoolDTO findOne(final Long id) {
		log.debug("Request to get VLanPool : {}", id);
		final VLanPool vLanPool = vLanPoolRepository.findOne(id);
		return vLanPoolMapper.toDto(vLanPool);
	}

	/**
	 * Delete the vLanLink by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(final Long id) {
		log.debug("Request to delete VLanLink : {}", id);
		vLanPoolRepository.delete(id);
	}
}
