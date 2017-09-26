package jhipster.monolithic.angular.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.repository.VLanLinkRepository;
import jhipster.monolithic.angular.service.VLanLinkService;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import jhipster.monolithic.angular.service.mapper.VLanLinkMapper;
import jhipster.monolithic.angular.service.mapper.VLanMapper;

/**
 * Service Implementation for managing VLanLink.
 */
@Service
@Transactional
public class VLanLinkServiceImpl implements VLanLinkService {

	private final Logger log = LoggerFactory.getLogger(VLanLinkServiceImpl.class);

	private final VLanLinkRepository vLanLinkRepository;

	private final VLanLinkMapper vLanLinkMapper;

	private final VLanMapper vLanMapper;

	public VLanLinkServiceImpl(final VLanLinkRepository vLanLinkRepository, final VLanLinkMapper vLanLinkMapper, final VLanMapper vLanMapper) {
		this.vLanLinkRepository = vLanLinkRepository;
		this.vLanLinkMapper = vLanLinkMapper;
		this.vLanMapper = vLanMapper;
	}

	/**
	 * Save a vLanLink.
	 *
	 * @param vLanLinkDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public VLanLinkDTO save(final VLanLinkDTO vLanLinkDTO) {
		log.debug("Request to save VLanLink : {}", vLanLinkDTO);

		VLanLink vLanLink = vLanLinkMapper.toEntity(vLanLinkDTO);
		if (vLanLinkDTO.getVLans() != null) {
			vLanLink.setVLanLinks(new HashSet<>(vLanMapper.toEntity(vLanLinkDTO.getVLans())));
		}
		vLanLink = vLanLinkRepository.save(vLanLink);
		return vLanLinkMapper.toDto(vLanLink);
	}

	/**
	 * Get all the vLanLinks.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VLanLinkDTO> findAll() {
		log.debug("Request to get all VLanLinks");
		return vLanLinkRepository.findAll().stream().map(vLanLinkMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
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
	public VLanLinkDTO findOne(final Long id) {
		log.debug("Request to get VLanLink : {}", id);
		final VLanLink vLanLink = vLanLinkRepository.findOne(id);
		return vLanLinkMapper.toDto(vLanLink);
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
		vLanLinkRepository.delete(id);
	}
}
