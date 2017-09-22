package jhipster.monolithic.angular.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.monolithic.angular.service.IpV4AddressService;
import jhipster.monolithic.angular.web.rest.util.HeaderUtil;
import jhipster.monolithic.angular.web.rest.util.PaginationUtil;
import jhipster.monolithic.angular.service.dto.IpV4AddressDTO;
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
 * REST controller for managing IpV4Address.
 */
@RestController
@RequestMapping("/api")
public class IpV4AddressResource {

    private final Logger log = LoggerFactory.getLogger(IpV4AddressResource.class);

    private static final String ENTITY_NAME = "ipV4Address";

    private final IpV4AddressService ipV4AddressService;

    public IpV4AddressResource(IpV4AddressService ipV4AddressService) {
        this.ipV4AddressService = ipV4AddressService;
    }

    /**
     * POST  /ip-v-4-addresses : Create a new ipV4Address.
     *
     * @param ipV4AddressDTO the ipV4AddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ipV4AddressDTO, or with status 400 (Bad Request) if the ipV4Address has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ip-v-4-addresses")
    @Timed
    public ResponseEntity<IpV4AddressDTO> createIpV4Address(@Valid @RequestBody IpV4AddressDTO ipV4AddressDTO) throws URISyntaxException {
        log.debug("REST request to save IpV4Address : {}", ipV4AddressDTO);
        if (ipV4AddressDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ipV4Address cannot already have an ID")).body(null);
        }
        IpV4AddressDTO result = ipV4AddressService.save(ipV4AddressDTO);
        return ResponseEntity.created(new URI("/api/ip-v-4-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ip-v-4-addresses : Updates an existing ipV4Address.
     *
     * @param ipV4AddressDTO the ipV4AddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ipV4AddressDTO,
     * or with status 400 (Bad Request) if the ipV4AddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the ipV4AddressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ip-v-4-addresses")
    @Timed
    public ResponseEntity<IpV4AddressDTO> updateIpV4Address(@Valid @RequestBody IpV4AddressDTO ipV4AddressDTO) throws URISyntaxException {
        log.debug("REST request to update IpV4Address : {}", ipV4AddressDTO);
        if (ipV4AddressDTO.getId() == null) {
            return createIpV4Address(ipV4AddressDTO);
        }
        IpV4AddressDTO result = ipV4AddressService.save(ipV4AddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ipV4AddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ip-v-4-addresses : get all the ipV4Addresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ipV4Addresses in body
     */
    @GetMapping("/ip-v-4-addresses")
    @Timed
    public ResponseEntity<List<IpV4AddressDTO>> getAllIpV4Addresses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of IpV4Addresses");
        Page<IpV4AddressDTO> page = ipV4AddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ip-v-4-addresses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ip-v-4-addresses/:id : get the "id" ipV4Address.
     *
     * @param id the id of the ipV4AddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ipV4AddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ip-v-4-addresses/{id}")
    @Timed
    public ResponseEntity<IpV4AddressDTO> getIpV4Address(@PathVariable Long id) {
        log.debug("REST request to get IpV4Address : {}", id);
        IpV4AddressDTO ipV4AddressDTO = ipV4AddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ipV4AddressDTO));
    }

    /**
     * DELETE  /ip-v-4-addresses/:id : delete the "id" ipV4Address.
     *
     * @param id the id of the ipV4AddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ip-v-4-addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteIpV4Address(@PathVariable Long id) {
        log.debug("REST request to delete IpV4Address : {}", id);
        ipV4AddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
