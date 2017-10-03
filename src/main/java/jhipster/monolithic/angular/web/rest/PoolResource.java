package jhipster.monolithic.angular.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.monolithic.angular.service.PoolService;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.web.rest.util.PaginationUtil;
import jhipster.monolithic.angular.service.dto.IpPoolDTO;
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
 * REST controller for managing Pool.
 */
@RestController
@RequestMapping("/api")
public class PoolResource {

    private final Logger log = LoggerFactory.getLogger(PoolResource.class);

    private static final String ENTITY_NAME = "pool";

    private final PoolService poolService;

    public PoolResource(PoolService poolService) {
        this.poolService = poolService;
    }

    /**
     * POST  /pools : Create a new pool.
     *
     * @param poolDTO the poolDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new poolDTO, or with status 400 (Bad Request) if the pool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pools")
    @Timed
    public ResponseEntity<IpPoolDTO> createPool(@Valid @RequestBody IpPoolDTO poolDTO) throws URISyntaxException {
        log.debug("REST request to save Pool : {}", poolDTO);
        if (poolDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pool cannot already have an ID")).body(null);
        }
        IpPoolDTO result = poolService.save(poolDTO);
        return ResponseEntity.created(new URI("/api/pools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pools : Updates an existing pool.
     *
     * @param poolDTO the poolDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated poolDTO,
     * or with status 400 (Bad Request) if the poolDTO is not valid,
     * or with status 500 (Internal Server Error) if the poolDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pools")
    @Timed
    public ResponseEntity<IpPoolDTO> updatePool(@Valid @RequestBody IpPoolDTO poolDTO) throws URISyntaxException {
        log.debug("REST request to update Pool : {}", poolDTO);
        if (poolDTO.getId() == null) {
            return createPool(poolDTO);
        }
        IpPoolDTO result = poolService.save(poolDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, poolDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pools : get all the pools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pools in body
     */
    @GetMapping("/pools")
    @Timed
    public ResponseEntity<List<IpPoolDTO>> getAllPools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Pools");
        Page<IpPoolDTO> page = poolService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pools/:id : get the "id" pool.
     *
     * @param id the id of the poolDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the poolDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pools/{id}")
    @Timed
    public ResponseEntity<IpPoolDTO> getPool(@PathVariable Long id) {
        log.debug("REST request to get Pool : {}", id);
        IpPoolDTO poolDTO = poolService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(poolDTO));
    }

    /**
     * DELETE  /pools/:id : delete the "id" pool.
     *
     * @param id the id of the poolDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pools/{id}")
    @Timed
    public ResponseEntity<Void> deletePool(@PathVariable Long id) {
        log.debug("REST request to delete Pool : {}", id);
        poolService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
