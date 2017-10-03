package jhipster.monolithic.angular.web.rest;

import com.codahale.metrics.annotation.Timed;

import jhipster.monolithic.angular.service.L2DomainService;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.web.rest.util.PaginationUtil;
import jhipster.monolithic.angular.service.dto.IpPoolDTO;
import jhipster.monolithic.angular.service.dto.L2DomainDTO;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VLanLink.
 */
@RestController
@RequestMapping("/api")
public class L2DomainResource {

    private final Logger log = LoggerFactory.getLogger(L2DomainResource.class);

    private static final String ENTITY_NAME = "l2Domain";

    private static final String URL = "/l2Domains";

    private final L2DomainService l2DomainService;

    public L2DomainResource(L2DomainService l2DomainService) {
        this.l2DomainService = l2DomainService;
    }

    /**
     * POST  {@value #URL} : Create a new l2Domain.
     *
     * @param l2DomainDTO the l2DomainDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new l2DomainDTO, or with status 400 (Bad Request) if the l2Domain has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(URL)
    @Timed
    public ResponseEntity<L2DomainDTO> createL2Domain(@Valid @RequestBody L2DomainDTO l2DomainDTO) throws URISyntaxException {
        log.debug("REST request to save L2Domain : {}", l2DomainDTO);
        if (l2DomainDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new l2Domain cannot already have an ID")).body(null);
        }
        L2DomainDTO result = l2DomainService.save(l2DomainDTO);
        return ResponseEntity.created(new URI("/api" + URL + "/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  {@value #URL} : Updates an existing l2Domain.
     *
     * @param l2DomainDTO the l2DomainDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated l2DomainDTO,
     * or with status 400 (Bad Request) if the l2DomainDTO is not valid,
     * or with status 500 (Internal Server Error) if the l2DomainDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping(URL)
    @Timed
    public ResponseEntity<L2DomainDTO> updateL2Domain(@Valid @RequestBody L2DomainDTO l2DomainDTO) throws URISyntaxException {
        log.debug("REST request to update L2Domain : {}", l2DomainDTO);
        if (l2DomainDTO.getId() == null) {
            return createL2Domain(l2DomainDTO);
        }
        L2DomainDTO result = l2DomainService.save(l2DomainDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, l2DomainDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  {@value #URL} : get all the l2Domains.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of l2DomainDTOs in body
     */
    @GetMapping(URL)
    @Timed
    public ResponseEntity<List<L2DomainDTO>> getAllL2Domains(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of L2Domains");
        Page<L2DomainDTO> page = l2DomainService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api" + URL);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }

    /**
     * GET  {@value #URL}/:id : get the "id" l2Domain.
     *
     * @param id the id of the l2DomainDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the l2DomainDTO, or with status 404 (Not Found)
     */
    @GetMapping(URL + "/{id}")
    @Timed
    public ResponseEntity<L2DomainDTO> getL2Domain(@PathVariable Long id) {
        log.debug("REST request to get L2Domain : {}", id);
        L2DomainDTO vLanLinkDTO = l2DomainService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vLanLinkDTO));
    }

    /**
     * DELETE  {@value #URL}/:id : delete the "id" vLanLink.
     *
     * @param id the id of the vLanLinkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping(URL + "/{id}")
    @Timed
    public ResponseEntity<Void> deleteL2Domain(@PathVariable Long id) {
        log.debug("REST request to delete L2Domain : {}", id);
        l2DomainService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
