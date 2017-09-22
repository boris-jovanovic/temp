package jhipster.monolithic.angular.web.rest;

import jhipster.monolithic.angular.IpamApp;

import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.repository.VLanRepository;
import jhipster.monolithic.angular.service.VLanService;
import jhipster.monolithic.angular.service.dto.VLanDTO;
import jhipster.monolithic.angular.service.mapper.VLanMapper;
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
 * Test class for the VLanResource REST controller.
 *
 * @see VLanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpamApp.class)
public class VLanResourceIntTest {

    private static final String DEFAULT_V_LAN_ID = "AAAAAAAAAA";
    private static final String UPDATED_V_LAN_ID = "BBBBBBBBBB";

    @Autowired
    private VLanRepository vLanRepository;

    @Autowired
    private VLanMapper vLanMapper;

    @Autowired
    private VLanService vLanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVLanMockMvc;

    private VLan vLan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VLanResource vLanResource = new VLanResource(vLanService);
        this.restVLanMockMvc = MockMvcBuilders.standaloneSetup(vLanResource)
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
    public static VLan createEntity(EntityManager em) {
        VLan vLan = new VLan()
            .vLanId(DEFAULT_V_LAN_ID);
        return vLan;
    }

    @Before
    public void initTest() {
        vLan = createEntity(em);
    }

    @Test
    @Transactional
    public void createVLan() throws Exception {
        int databaseSizeBeforeCreate = vLanRepository.findAll().size();

        // Create the VLan
        VLanDTO vLanDTO = vLanMapper.toDto(vLan);
        restVLanMockMvc.perform(post("/api/v-lans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanDTO)))
            .andExpect(status().isCreated());

        // Validate the VLan in the database
        List<VLan> vLanList = vLanRepository.findAll();
        assertThat(vLanList).hasSize(databaseSizeBeforeCreate + 1);
        VLan testVLan = vLanList.get(vLanList.size() - 1);
        assertThat(testVLan.getvLanId()).isEqualTo(DEFAULT_V_LAN_ID);
    }

    @Test
    @Transactional
    public void createVLanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vLanRepository.findAll().size();

        // Create the VLan with an existing ID
        vLan.setId(1L);
        VLanDTO vLanDTO = vLanMapper.toDto(vLan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVLanMockMvc.perform(post("/api/v-lans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VLan in the database
        List<VLan> vLanList = vLanRepository.findAll();
        assertThat(vLanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkvLanIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vLanRepository.findAll().size();
        // set the field null
        vLan.setvLanId(null);

        // Create the VLan, which fails.
        VLanDTO vLanDTO = vLanMapper.toDto(vLan);

        restVLanMockMvc.perform(post("/api/v-lans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanDTO)))
            .andExpect(status().isBadRequest());

        List<VLan> vLanList = vLanRepository.findAll();
        assertThat(vLanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVLans() throws Exception {
        // Initialize the database
        vLanRepository.saveAndFlush(vLan);

        // Get all the vLanList
        restVLanMockMvc.perform(get("/api/v-lans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vLan.getId().intValue())))
            .andExpect(jsonPath("$.[*].vLanId").value(hasItem(DEFAULT_V_LAN_ID.toString())));
    }

    @Test
    @Transactional
    public void getVLan() throws Exception {
        // Initialize the database
        vLanRepository.saveAndFlush(vLan);

        // Get the vLan
        restVLanMockMvc.perform(get("/api/v-lans/{id}", vLan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vLan.getId().intValue()))
            .andExpect(jsonPath("$.vLanId").value(DEFAULT_V_LAN_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVLan() throws Exception {
        // Get the vLan
        restVLanMockMvc.perform(get("/api/v-lans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVLan() throws Exception {
        // Initialize the database
        vLanRepository.saveAndFlush(vLan);
        int databaseSizeBeforeUpdate = vLanRepository.findAll().size();

        // Update the vLan
        VLan updatedVLan = vLanRepository.findOne(vLan.getId());
        updatedVLan
            .vLanId(UPDATED_V_LAN_ID);
        VLanDTO vLanDTO = vLanMapper.toDto(updatedVLan);

        restVLanMockMvc.perform(put("/api/v-lans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanDTO)))
            .andExpect(status().isOk());

        // Validate the VLan in the database
        List<VLan> vLanList = vLanRepository.findAll();
        assertThat(vLanList).hasSize(databaseSizeBeforeUpdate);
        VLan testVLan = vLanList.get(vLanList.size() - 1);
        assertThat(testVLan.getvLanId()).isEqualTo(UPDATED_V_LAN_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingVLan() throws Exception {
        int databaseSizeBeforeUpdate = vLanRepository.findAll().size();

        // Create the VLan
        VLanDTO vLanDTO = vLanMapper.toDto(vLan);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVLanMockMvc.perform(put("/api/v-lans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanDTO)))
            .andExpect(status().isCreated());

        // Validate the VLan in the database
        List<VLan> vLanList = vLanRepository.findAll();
        assertThat(vLanList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVLan() throws Exception {
        // Initialize the database
        vLanRepository.saveAndFlush(vLan);
        int databaseSizeBeforeDelete = vLanRepository.findAll().size();

        // Get the vLan
        restVLanMockMvc.perform(delete("/api/v-lans/{id}", vLan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VLan> vLanList = vLanRepository.findAll();
        assertThat(vLanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VLan.class);
        VLan vLan1 = new VLan();
        vLan1.setId(1L);
        VLan vLan2 = new VLan();
        vLan2.setId(vLan1.getId());
        assertThat(vLan1).isEqualTo(vLan2);
        vLan2.setId(2L);
        assertThat(vLan1).isNotEqualTo(vLan2);
        vLan1.setId(null);
        assertThat(vLan1).isNotEqualTo(vLan2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VLanDTO.class);
        VLanDTO vLanDTO1 = new VLanDTO();
        vLanDTO1.setId(1L);
        VLanDTO vLanDTO2 = new VLanDTO();
        assertThat(vLanDTO1).isNotEqualTo(vLanDTO2);
        vLanDTO2.setId(vLanDTO1.getId());
        assertThat(vLanDTO1).isEqualTo(vLanDTO2);
        vLanDTO2.setId(2L);
        assertThat(vLanDTO1).isNotEqualTo(vLanDTO2);
        vLanDTO1.setId(null);
        assertThat(vLanDTO1).isNotEqualTo(vLanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vLanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vLanMapper.fromId(null)).isNull();
    }
}
