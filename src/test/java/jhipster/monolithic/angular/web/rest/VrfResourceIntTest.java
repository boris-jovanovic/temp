package jhipster.monolithic.angular.web.rest;

import jhipster.monolithic.angular.IpamApp;

import jhipster.monolithic.angular.domain.Vrf;
import jhipster.monolithic.angular.repository.VrfRepository;
import jhipster.monolithic.angular.service.VrfService;
import jhipster.monolithic.angular.service.dto.VrfDTO;
import jhipster.monolithic.angular.service.mapper.VrfMapper;
import jhipster.monolithic.angular.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VrfResource REST controller.
 *
 * @see VrfResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpamApp.class)
public class VrfResourceIntTest {

    private static final String DEFAULT_VRF_ID = "AAAAAAAAAA";
    private static final String UPDATED_VRF_ID = "BBBBBBBBBB";

    @Autowired
    private VrfRepository vrfRepository;

    @Autowired
    private VrfMapper vrfMapper;

    @Autowired
    private VrfService vrfService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVrfMockMvc;

    private Vrf vrf;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VrfResource vrfResource = new VrfResource(vrfService);
        this.restVrfMockMvc = MockMvcBuilders.standaloneSetup(vrfResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vrf createEntity(EntityManager em) {
        Vrf vrf = new Vrf()
            .vrfId(DEFAULT_VRF_ID);
        return vrf;
    }

    @Before
    public void initTest() {
        vrf = createEntity(em);
    }

    @Test
    @Transactional
    public void createVrf() throws Exception {
        int databaseSizeBeforeCreate = vrfRepository.findAll().size();

        // Create the Vrf
        VrfDTO vrfDTO = vrfMapper.toDto(vrf);
        restVrfMockMvc.perform(post("/api/vrfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vrfDTO)))
            .andExpect(status().isCreated());

        // Validate the Vrf in the database
        List<Vrf> vrfList = vrfRepository.findAll();
        assertThat(vrfList).hasSize(databaseSizeBeforeCreate + 1);
        Vrf testVrf = vrfList.get(vrfList.size() - 1);
        assertThat(testVrf.getVrfId()).isEqualTo(DEFAULT_VRF_ID);
    }

    @Test
    @Transactional
    public void createVrfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vrfRepository.findAll().size();

        // Create the Vrf with an existing ID
        vrf.setId(1L);
        VrfDTO vrfDTO = vrfMapper.toDto(vrf);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVrfMockMvc.perform(post("/api/vrfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vrfDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vrf in the database
        List<Vrf> vrfList = vrfRepository.findAll();
        assertThat(vrfList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkVrfIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vrfRepository.findAll().size();
        // set the field null
        vrf.setVrfId(null);

        // Create the Vrf, which fails.
        VrfDTO vrfDTO = vrfMapper.toDto(vrf);

        restVrfMockMvc.perform(post("/api/vrfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vrfDTO)))
            .andExpect(status().isBadRequest());

        List<Vrf> vrfList = vrfRepository.findAll();
        assertThat(vrfList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVrfs() throws Exception {
        // Initialize the database
        vrfRepository.saveAndFlush(vrf);

        // Get all the vrfList
        restVrfMockMvc.perform(get("/api/vrfs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vrf.getId().intValue())))
            .andExpect(jsonPath("$.[*].vrfId").value(hasItem(DEFAULT_VRF_ID.toString())));
    }

    @Test
    @Transactional
    public void getVrf() throws Exception {
        // Initialize the database
        vrfRepository.saveAndFlush(vrf);

        // Get the vrf
        restVrfMockMvc.perform(get("/api/vrfs/{id}", vrf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vrf.getId().intValue()))
            .andExpect(jsonPath("$.vrfId").value(DEFAULT_VRF_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVrf() throws Exception {
        // Get the vrf
        restVrfMockMvc.perform(get("/api/vrfs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVrf() throws Exception {
        // Initialize the database
        vrfRepository.saveAndFlush(vrf);
        int databaseSizeBeforeUpdate = vrfRepository.findAll().size();

        // Update the vrf
        Vrf updatedVrf = vrfRepository.findOne(vrf.getId());
        updatedVrf
            .vrfId(UPDATED_VRF_ID);
        VrfDTO vrfDTO = vrfMapper.toDto(updatedVrf);

        restVrfMockMvc.perform(put("/api/vrfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vrfDTO)))
            .andExpect(status().isOk());

        // Validate the Vrf in the database
        List<Vrf> vrfList = vrfRepository.findAll();
        assertThat(vrfList).hasSize(databaseSizeBeforeUpdate);
        Vrf testVrf = vrfList.get(vrfList.size() - 1);
        assertThat(testVrf.getVrfId()).isEqualTo(UPDATED_VRF_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingVrf() throws Exception {
        int databaseSizeBeforeUpdate = vrfRepository.findAll().size();

        // Create the Vrf
        VrfDTO vrfDTO = vrfMapper.toDto(vrf);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVrfMockMvc.perform(put("/api/vrfs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vrfDTO)))
            .andExpect(status().isCreated());

        // Validate the Vrf in the database
        List<Vrf> vrfList = vrfRepository.findAll();
        assertThat(vrfList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVrf() throws Exception {
        // Initialize the database
        vrfRepository.saveAndFlush(vrf);
        int databaseSizeBeforeDelete = vrfRepository.findAll().size();

        // Get the vrf
        restVrfMockMvc.perform(delete("/api/vrfs/{id}", vrf.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vrf> vrfList = vrfRepository.findAll();
        assertThat(vrfList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vrf.class);
        Vrf vrf1 = new Vrf();
        vrf1.setId(1L);
        Vrf vrf2 = new Vrf();
        vrf2.setId(vrf1.getId());
        assertThat(vrf1).isEqualTo(vrf2);
        vrf2.setId(2L);
        assertThat(vrf1).isNotEqualTo(vrf2);
        vrf1.setId(null);
        assertThat(vrf1).isNotEqualTo(vrf2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VrfDTO.class);
        VrfDTO vrfDTO1 = new VrfDTO();
        vrfDTO1.setId(1L);
        VrfDTO vrfDTO2 = new VrfDTO();
        assertThat(vrfDTO1).isNotEqualTo(vrfDTO2);
        vrfDTO2.setId(vrfDTO1.getId());
        assertThat(vrfDTO1).isEqualTo(vrfDTO2);
        vrfDTO2.setId(2L);
        assertThat(vrfDTO1).isNotEqualTo(vrfDTO2);
        vrfDTO1.setId(null);
        assertThat(vrfDTO1).isNotEqualTo(vrfDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vrfMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vrfMapper.fromId(null)).isNull();
    }
}
