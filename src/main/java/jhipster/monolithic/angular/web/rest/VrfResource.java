package jhipster.monolithic.angular.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.monolithic.angular.service.VrfService;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.web.rest.util.PaginationUtil;
import jhipster.monolithic.angular.service.dto.VrfDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Vrf.
 */
@RestController
@RequestMapping("/api")
public class VrfResource {

    private final Logger log = LoggerFactory.getLogger(VrfResource.class);

    private static final String ENTITY_NAME = "vrf";

    private final VrfService vrfService;

    public VrfResource(VrfService vrfService) {
        this.vrfService = vrfService;
    }

    /**
     * POST  /vrfs : Create a new vrf.
     *
     * @param vrfDTO the vrfDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vrfDTO, or with status 400 (Bad Request) if the vrf has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vrfs")
    @Timed
    public ResponseEntity<VrfDTO> createVrf(@Valid @RequestBody VrfDTO vrfDTO) throws URISyntaxException {
        log.debug("REST request to save Vrf : {}", vrfDTO);
        if (vrfDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new vrf cannot already have an ID")).body(null);
        }
        VrfDTO result = vrfService.save(vrfDTO);
        return ResponseEntity.created(new URI("/api/vrfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vrfs : Updates an existing vrf.
     *
     * @param vrfDTO the vrfDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vrfDTO,
     * or with status 400 (Bad Request) if the vrfDTO is not valid,
     * or with status 500 (Internal Server Error) if the vrfDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vrfs")
    @Timed
    public ResponseEntity<VrfDTO> updateVrf(@Valid @RequestBody VrfDTO vrfDTO) throws URISyntaxException {
        log.debug("REST request to update Vrf : {}", vrfDTO);
        if (vrfDTO.getId() == null) {
            return createVrf(vrfDTO);
        }
        VrfDTO result = vrfService.save(vrfDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vrfDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vrfs : get all the vrfs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vrfs in body
     */
    @GetMapping("/vrfs")
    @Timed
    public ResponseEntity<List<VrfDTO>> getAllVrfs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Vrfs");
        Page<VrfDTO> page = vrfService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vrfs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /vrfs/:id : get the "id" vrf.
     *
     * @param id the id of the vrfDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vrfDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vrfs/{id}")
    @Timed
    public ResponseEntity<VrfDTO> getVrf(@PathVariable Long id) {
        log.debug("REST request to get Vrf : {}", id);
        VrfDTO vrfDTO = vrfService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vrfDTO));
    }

    /**
     * DELETE  /vrfs/:id : delete the "id" vrf.
     *
     * @param id the id of the vrfDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vrfs/{id}")
    @Timed
    public ResponseEntity<Void> deleteVrf(@PathVariable Long id) {
        log.debug("REST request to delete Vrf : {}", id);
        vrfService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
