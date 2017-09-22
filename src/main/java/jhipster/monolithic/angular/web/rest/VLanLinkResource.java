package jhipster.monolithic.angular.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.monolithic.angular.service.VLanLinkService;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
public class VLanLinkResource {

    private final Logger log = LoggerFactory.getLogger(VLanLinkResource.class);

    private static final String ENTITY_NAME = "vLanLink";

    private final VLanLinkService vLanLinkService;

    public VLanLinkResource(VLanLinkService vLanLinkService) {
        this.vLanLinkService = vLanLinkService;
    }

    /**
     * POST  /v-lan-links : Create a new vLanLink.
     *
     * @param vLanLinkDTO the vLanLinkDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vLanLinkDTO, or with status 400 (Bad Request) if the vLanLink has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/v-lan-links")
    @Timed
    public ResponseEntity<VLanLinkDTO> createVLanLink(@Valid @RequestBody VLanLinkDTO vLanLinkDTO) throws URISyntaxException {
        log.debug("REST request to save VLanLink : {}", vLanLinkDTO);
        if (vLanLinkDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new vLanLink cannot already have an ID")).body(null);
        }
        VLanLinkDTO result = vLanLinkService.save(vLanLinkDTO);
        return ResponseEntity.created(new URI("/api/v-lan-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /v-lan-links : Updates an existing vLanLink.
     *
     * @param vLanLinkDTO the vLanLinkDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vLanLinkDTO,
     * or with status 400 (Bad Request) if the vLanLinkDTO is not valid,
     * or with status 500 (Internal Server Error) if the vLanLinkDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/v-lan-links")
    @Timed
    public ResponseEntity<VLanLinkDTO> updateVLanLink(@Valid @RequestBody VLanLinkDTO vLanLinkDTO) throws URISyntaxException {
        log.debug("REST request to update VLanLink : {}", vLanLinkDTO);
        if (vLanLinkDTO.getId() == null) {
            return createVLanLink(vLanLinkDTO);
        }
        VLanLinkDTO result = vLanLinkService.save(vLanLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vLanLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /v-lan-links : get all the vLanLinks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vLanLinks in body
     */
    @GetMapping("/v-lan-links")
    @Timed
    public List<VLanLinkDTO> getAllVLanLinks() {
        log.debug("REST request to get all VLanLinks");
        return vLanLinkService.findAll();
        }

    /**
     * GET  /v-lan-links/:id : get the "id" vLanLink.
     *
     * @param id the id of the vLanLinkDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vLanLinkDTO, or with status 404 (Not Found)
     */
    @GetMapping("/v-lan-links/{id}")
    @Timed
    public ResponseEntity<VLanLinkDTO> getVLanLink(@PathVariable Long id) {
        log.debug("REST request to get VLanLink : {}", id);
        VLanLinkDTO vLanLinkDTO = vLanLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vLanLinkDTO));
    }

    /**
     * DELETE  /v-lan-links/:id : delete the "id" vLanLink.
     *
     * @param id the id of the vLanLinkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/v-lan-links/{id}")
    @Timed
    public ResponseEntity<Void> deleteVLanLink(@PathVariable Long id) {
        log.debug("REST request to delete VLanLink : {}", id);
        vLanLinkService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
