package jhipster.monolithic.angular.web.rest;

import jhipster.monolithic.angular.IpamApp;

import jhipster.monolithic.angular.domain.VLanLink;
import jhipster.monolithic.angular.repository.VLanLinkRepository;
import jhipster.monolithic.angular.service.VLanLinkService;
import jhipster.monolithic.angular.service.dto.VLanLinkDTO;
import jhipster.monolithic.angular.service.mapper.VLanLinkMapper;
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
 * Test class for the VLanLinkResource REST controller.
 *
 * @see VLanLinkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpamApp.class)
public class VLanLinkResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private VLanLinkRepository vLanLinkRepository;

    @Autowired
    private VLanLinkMapper vLanLinkMapper;

    @Autowired
    private VLanLinkService vLanLinkService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVLanLinkMockMvc;

    private VLanLink vLanLink;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VLanLinkResource vLanLinkResource = new VLanLinkResource(vLanLinkService);
        this.restVLanLinkMockMvc = MockMvcBuilders.standaloneSetup(vLanLinkResource)
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
    public static VLanLink createEntity(EntityManager em) {
        VLanLink vLanLink = new VLanLink()
            .name(DEFAULT_NAME);
        return vLanLink;
    }

    @Before
    public void initTest() {
        vLanLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createVLanLink() throws Exception {
        int databaseSizeBeforeCreate = vLanLinkRepository.findAll().size();

        // Create the VLanLink
        VLanLinkDTO vLanLinkDTO = vLanLinkMapper.toDto(vLanLink);
        restVLanLinkMockMvc.perform(post("/api/v-lan-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the VLanLink in the database
        List<VLanLink> vLanLinkList = vLanLinkRepository.findAll();
        assertThat(vLanLinkList).hasSize(databaseSizeBeforeCreate + 1);
        VLanLink testVLanLink = vLanLinkList.get(vLanLinkList.size() - 1);
        assertThat(testVLanLink.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createVLanLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vLanLinkRepository.findAll().size();

        // Create the VLanLink with an existing ID
        vLanLink.setId(1L);
        VLanLinkDTO vLanLinkDTO = vLanLinkMapper.toDto(vLanLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVLanLinkMockMvc.perform(post("/api/v-lan-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VLanLink in the database
        List<VLanLink> vLanLinkList = vLanLinkRepository.findAll();
        assertThat(vLanLinkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = vLanLinkRepository.findAll().size();
        // set the field null
        vLanLink.setName(null);

        // Create the VLanLink, which fails.
        VLanLinkDTO vLanLinkDTO = vLanLinkMapper.toDto(vLanLink);

        restVLanLinkMockMvc.perform(post("/api/v-lan-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanLinkDTO)))
            .andExpect(status().isBadRequest());

        List<VLanLink> vLanLinkList = vLanLinkRepository.findAll();
        assertThat(vLanLinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVLanLinks() throws Exception {
        // Initialize the database
        vLanLinkRepository.saveAndFlush(vLanLink);

        // Get all the vLanLinkList
        restVLanLinkMockMvc.perform(get("/api/v-lan-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vLanLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getVLanLink() throws Exception {
        // Initialize the database
        vLanLinkRepository.saveAndFlush(vLanLink);

        // Get the vLanLink
        restVLanLinkMockMvc.perform(get("/api/v-lan-links/{id}", vLanLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vLanLink.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVLanLink() throws Exception {
        // Get the vLanLink
        restVLanLinkMockMvc.perform(get("/api/v-lan-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVLanLink() throws Exception {
        // Initialize the database
        vLanLinkRepository.saveAndFlush(vLanLink);
        int databaseSizeBeforeUpdate = vLanLinkRepository.findAll().size();

        // Update the vLanLink
        VLanLink updatedVLanLink = vLanLinkRepository.findOne(vLanLink.getId());
        updatedVLanLink
            .name(UPDATED_NAME);
        VLanLinkDTO vLanLinkDTO = vLanLinkMapper.toDto(updatedVLanLink);

        restVLanLinkMockMvc.perform(put("/api/v-lan-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanLinkDTO)))
            .andExpect(status().isOk());

        // Validate the VLanLink in the database
        List<VLanLink> vLanLinkList = vLanLinkRepository.findAll();
        assertThat(vLanLinkList).hasSize(databaseSizeBeforeUpdate);
        VLanLink testVLanLink = vLanLinkList.get(vLanLinkList.size() - 1);
        assertThat(testVLanLink.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingVLanLink() throws Exception {
        int databaseSizeBeforeUpdate = vLanLinkRepository.findAll().size();

        // Create the VLanLink
        VLanLinkDTO vLanLinkDTO = vLanLinkMapper.toDto(vLanLink);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVLanLinkMockMvc.perform(put("/api/v-lan-links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vLanLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the VLanLink in the database
        List<VLanLink> vLanLinkList = vLanLinkRepository.findAll();
        assertThat(vLanLinkList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVLanLink() throws Exception {
        // Initialize the database
        vLanLinkRepository.saveAndFlush(vLanLink);
        int databaseSizeBeforeDelete = vLanLinkRepository.findAll().size();

        // Get the vLanLink
        restVLanLinkMockMvc.perform(delete("/api/v-lan-links/{id}", vLanLink.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VLanLink> vLanLinkList = vLanLinkRepository.findAll();
        assertThat(vLanLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VLanLink.class);
        VLanLink vLanLink1 = new VLanLink();
        vLanLink1.setId(1L);
        VLanLink vLanLink2 = new VLanLink();
        vLanLink2.setId(vLanLink1.getId());
        assertThat(vLanLink1).isEqualTo(vLanLink2);
        vLanLink2.setId(2L);
        assertThat(vLanLink1).isNotEqualTo(vLanLink2);
        vLanLink1.setId(null);
        assertThat(vLanLink1).isNotEqualTo(vLanLink2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VLanLinkDTO.class);
        VLanLinkDTO vLanLinkDTO1 = new VLanLinkDTO();
        vLanLinkDTO1.setId(1L);
        VLanLinkDTO vLanLinkDTO2 = new VLanLinkDTO();
        assertThat(vLanLinkDTO1).isNotEqualTo(vLanLinkDTO2);
        vLanLinkDTO2.setId(vLanLinkDTO1.getId());
        assertThat(vLanLinkDTO1).isEqualTo(vLanLinkDTO2);
        vLanLinkDTO2.setId(2L);
        assertThat(vLanLinkDTO1).isNotEqualTo(vLanLinkDTO2);
        vLanLinkDTO1.setId(null);
        assertThat(vLanLinkDTO1).isNotEqualTo(vLanLinkDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vLanLinkMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vLanLinkMapper.fromId(null)).isNull();
    }
}
