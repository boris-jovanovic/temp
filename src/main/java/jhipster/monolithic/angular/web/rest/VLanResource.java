package jhipster.monolithic.angular.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.monolithic.angular.service.VLanService;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.web.rest.util.PaginationUtil;
import jhipster.monolithic.angular.service.dto.VLanDTO;
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
 * REST controller for managing VLan.
 */
@RestController
@RequestMapping("/api")
public class VLanResource {

    private final Logger log = LoggerFactory.getLogger(VLanResource.class);

    private static final String ENTITY_NAME = "vLan";

    private final VLanService vLanService;

    public VLanResource(VLanService vLanService) {
        this.vLanService = vLanService;
    }

    /**
     * POST  /v-lans : Create a new vLan.
     *
     * @param vLanDTO the vLanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vLanDTO, or with status 400 (Bad Request) if the vLan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/v-lans")
    @Timed
    public ResponseEntity<VLanDTO> createVLan(@Valid @RequestBody VLanDTO vLanDTO) throws URISyntaxException {
        log.debug("REST request to save VLan : {}", vLanDTO);
        if (vLanDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new vLan cannot already have an ID")).body(null);
        }
        VLanDTO result = vLanService.save(vLanDTO);
        return ResponseEntity.created(new URI("/api/v-lans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /v-lans : Updates an existing vLan.
     *
     * @param vLanDTO the vLanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vLanDTO,
     * or with status 400 (Bad Request) if the vLanDTO is not valid,
     * or with status 500 (Internal Server Error) if the vLanDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/v-lans")
    @Timed
    public ResponseEntity<VLanDTO> updateVLan(@Valid @RequestBody VLanDTO vLanDTO) throws URISyntaxException {
        log.debug("REST request to update VLan : {}", vLanDTO);
        if (vLanDTO.getId() == null) {
            return createVLan(vLanDTO);
        }
        VLanDTO result = vLanService.save(vLanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vLanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /v-lans : get all the vLans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vLans in body
     */
    @GetMapping("/v-lans")
    @Timed
    public ResponseEntity<List<VLanDTO>> getAllVLans(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of VLans");
        Page<VLanDTO> page = vLanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/v-lans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /v-lans/:id : get the "id" vLan.
     *
     * @param id the id of the vLanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vLanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/v-lans/{id}")
    @Timed
    public ResponseEntity<VLanDTO> getVLan(@PathVariable Long id) {
        log.debug("REST request to get VLan : {}", id);
        VLanDTO vLanDTO = vLanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vLanDTO));
    }

    /**
     * DELETE  /v-lans/:id : delete the "id" vLan.
     *
     * @param id the id of the vLanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/v-lans/{id}")
    @Timed
    public ResponseEntity<Void> deleteVLan(@PathVariable Long id) {
        log.debug("REST request to delete VLan : {}", id);
        vLanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
