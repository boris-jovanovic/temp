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
import jhipster.monolithic.angular.service.L2DomainService;
import jhipster.monolithic.angular.service.dto.L2DomainDTO;
import jhipster.monolithic.angular.service.mapper.*;

/**
 * Service Implementation for managing VLanLink.
 */
@Service
@Transactional
public class L2DomainServiceImpl implements L2DomainService {

	private final Logger log = LoggerFactory.getLogger(L2DomainServiceImpl.class);

	private final L2DomainRepository l2DomainRepository;

	private final L2DomainMapper l2DomainMapper;

	private final VLanMapper vLanMapper;

	public L2DomainServiceImpl(final L2DomainRepository l2DomainRepository, final L2DomainMapper l2DomainMapper, final VLanMapper vLanMapper) {
		this.l2DomainRepository = l2DomainRepository;
		this.l2DomainMapper = l2DomainMapper;
		this.vLanMapper = vLanMapper;
	}

	/**
	 * Save a vLanLink.
	 *
	 * @param l2DomainDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public L2DomainDTO save(final L2DomainDTO l2DomainDTO) {
		log.debug("Request to save VLanLink : {}", l2DomainDTO);

		L2Domain vLanLink = l2DomainMapper.toEntity(l2DomainDTO);
		if (l2DomainDTO.getVLans() != null) {
			vLanLink.setVLans(new HashSet<>(vLanMapper.toEntity(l2DomainDTO.getVLans())));
		}
		vLanLink = l2DomainRepository.save(vLanLink);
		return l2DomainMapper.toDto(vLanLink);
	}

	/**
	 * Get all the vLanLinks.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<L2DomainDTO> findAll(Pageable pageable) {
		log.debug("Request to get all VLanLinks");
		return l2DomainRepository.findAll(pageable).map(l2DomainMapper::toDto);
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
	public L2DomainDTO findOne(final Long id) {
		log.debug("Request to get VLanLink : {}", id);
		final L2Domain l2Domain = l2DomainRepository.findOne(id);
		return l2DomainMapper.toDto(l2Domain);
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
		l2DomainRepository.delete(id);
	}
}
