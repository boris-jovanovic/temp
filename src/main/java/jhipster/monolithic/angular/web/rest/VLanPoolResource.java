package jhipster.monolithic.angular.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import jhipster.monolithic.angular.service.VLanPoolService;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.web.rest.util.PaginationUtil;

/**
 * REST controller for managing VLanLink.
 */
@RestController
@RequestMapping("/api")
public class VLanPoolResource {

	private final Logger log = LoggerFactory.getLogger(L2DomainResource.class);

	private static final String ENTITY_NAME = "vLanPool";

	private static final String URL = "/v-lan-pools";

	private final VLanPoolService vLanPoolService;

	public VLanPoolResource(final VLanPoolService vLanPoolService) {
		this.vLanPoolService = vLanPoolService;
	}

	/**
	 * POST {@value #URL} : Create a new vLanPool.
	 *
	 * @param vLanPoolDTO
	 *            the vLanPoolDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new vLanPoolDTO, or with
	 *         status 400 (Bad Request) if the vLanPool has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping(URL)
	@Timed
	public ResponseEntity<VLanPoolDTO> createVLanPool(@Valid @RequestBody final VLanPoolDTO vLanPoolDTO) throws URISyntaxException {
		log.debug("REST request to save VLanPool : {}", vLanPoolDTO);
		if (vLanPoolDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new vLanPool cannot already have an ID")).body(null);
		}
		final VLanPoolDTO result = vLanPoolService.save(vLanPoolDTO);
		return ResponseEntity.created(new URI("/api" + URL + "/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT {@value #URL} : Updates an existing vLanPool.
	 *
	 * @param vLanPoolDTO
	 *            the vLanPoolDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated vLanPoolDTO, or with
	 *         status 400 (Bad Request) if the vLanPoolDTO is not valid, or with status 500 (Internal
	 *         Server Error) if the vLanPoolDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping(URL)
	@Timed
	public ResponseEntity<VLanPoolDTO> updateVLanPool(@Valid @RequestBody final VLanPoolDTO vLanPoolDTO) throws URISyntaxException {
		log.debug("REST request to update VLanPool : {}", vLanPoolDTO);
		if (vLanPoolDTO.getId() == null) {
			return createVLanPool(vLanPoolDTO);
		}
		final VLanPoolDTO result = vLanPoolService.save(vLanPoolDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vLanPoolDTO.getId().toString())).body(result);
	}

	/**
	 * GET {@value #URL} : get all the l2Domains.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of l2DomainDTOs in body
	 */
	@GetMapping(URL)
	@Timed
	public ResponseEntity<List<VLanPoolDTO>> getAllVLanPools(@ApiParam final Pageable pageable) {
		log.debug("REST request to get a page of L2Domains");
		final Page<VLanPoolDTO> page = vLanPoolService.findAll(pageable);
		final HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api" + URL);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET {@value #URL}/:id : get the "id" vLanPool.
	 *
	 * @param id
	 *            the id of the vLanPoolDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the vLanPoolDTO, or with status 404
	 *         (Not Found)
	 */
	@GetMapping(URL + "/{id}")
	@Timed
	public ResponseEntity<VLanPoolDTO> getVLanPool(@PathVariable final Long id) {
		log.debug("REST request to get VLanPool : {}", id);
		final VLanPoolDTO vLanPoolDTO = vLanPoolService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vLanPoolDTO));
	}

	/**
	 * DELETE {@value #URL}/:id : delete the "id" vLanPool.
	 *
	 * @param id
	 *            the id of the vLanLinkDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping(URL + "/{id}")
	@Timed
	public ResponseEntity<Void> deleteVLanPool(@PathVariable final Long id) {
		log.debug("REST request to delete VLanPool : {}", id);
		vLanPoolService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
