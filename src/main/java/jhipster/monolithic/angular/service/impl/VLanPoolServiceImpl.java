package jhipster.monolithic.angular.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jhipster.monolithic.angular.domain.*;
import jhipster.monolithic.angular.repository.L2DomainRepository;
import jhipster.monolithic.angular.repository.VLanPoolRepository;
import jhipster.monolithic.angular.service.L2DomainService;
import jhipster.monolithic.angular.service.VLanPoolService;
import jhipster.monolithic.angular.service.dto.L2DomainDTO;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;
import jhipster.monolithic.angular.service.mapper.*;

/**
 * Service Implementation for managing VLanLink.
 */
@Service
@Transactional
public class VLanPoolServiceImpl implements VLanPoolService {

	private final Logger log = LoggerFactory.getLogger(VLanPoolServiceImpl.class);

	private final L2DomainRepository l2DomainRepository;

	private final VLanPoolMapper vLanPoolMapper;

	private final VLanMapper vLanMapper;

	private VLanPoolRepository vLanPoolRepository;

	public VLanPoolServiceImpl(final L2DomainRepository l2DomainRepository, final VLanPoolMapper vLanPoolMapper, final VLanMapper vLanMapper, final VLanPoolRepository vLanPoolRepository) {
		this.l2DomainRepository = l2DomainRepository;
		this.vLanPoolMapper = vLanPoolMapper;
		this.vLanMapper = vLanMapper;
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
	public VLanPoolDTO save(VLanPoolDTO vLanPoolDTO) {
		log.debug("Request to save VLanPoolLink : {}", vLanPoolDTO);

		VLanPool vLanLink = vLanPoolMapper.toEntity(vLanPoolDTO);
		if (vLanPoolDTO.getVLans() != null) {
			vLanLink.setVLans(new HashSet<>(vLanMapper.toEntity(vLanPoolDTO.getVLans())));
		}
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
	public Page<VLanPoolDTO> findAll(Pageable pageable) {
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
	public VLanPoolDTO findOne(Long id) {
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
