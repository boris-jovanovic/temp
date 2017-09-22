package jhipster.monolithic.angular.web.rest;

import jhipster.monolithic.angular.IpamApp;

import jhipster.monolithic.angular.domain.IpV4Address;
import jhipster.monolithic.angular.repository.IpV4AddressRepository;
import jhipster.monolithic.angular.service.IpV4AddressService;
import jhipster.monolithic.angular.service.dto.IpV4AddressDTO;
import jhipster.monolithic.angular.service.mapper.IpV4AddressMapper;
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
 * Test class for the IpV4AddressResource REST controller.
 *
 * @see IpV4AddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpamApp.class)
public class IpV4AddressResourceIntTest {

    private static final String DEFAULT_IP_V_4_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_V_4_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private IpV4AddressRepository ipV4AddressRepository;

    @Autowired
    private IpV4AddressMapper ipV4AddressMapper;

    @Autowired
    private IpV4AddressService ipV4AddressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIpV4AddressMockMvc;

    private IpV4Address ipV4Address;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IpV4AddressResource ipV4AddressResource = new IpV4AddressResource(ipV4AddressService);
        this.restIpV4AddressMockMvc = MockMvcBuilders.standaloneSetup(ipV4AddressResource)
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
    public static IpV4Address createEntity(EntityManager em) {
        IpV4Address ipV4Address = new IpV4Address()
            .ipV4Address(DEFAULT_IP_V_4_ADDRESS);
        return ipV4Address;
    }

    @Before
    public void initTest() {
        ipV4Address = createEntity(em);
    }

    @Test
    @Transactional
    public void createIpV4Address() throws Exception {
        int databaseSizeBeforeCreate = ipV4AddressRepository.findAll().size();

        // Create the IpV4Address
        IpV4AddressDTO ipV4AddressDTO = ipV4AddressMapper.toDto(ipV4Address);
        restIpV4AddressMockMvc.perform(post("/api/ip-v-4-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipV4AddressDTO)))
            .andExpect(status().isCreated());

        // Validate the IpV4Address in the database
        List<IpV4Address> ipV4AddressList = ipV4AddressRepository.findAll();
        assertThat(ipV4AddressList).hasSize(databaseSizeBeforeCreate + 1);
        IpV4Address testIpV4Address = ipV4AddressList.get(ipV4AddressList.size() - 1);
        assertThat(testIpV4Address.getIpV4Address()).isEqualTo(DEFAULT_IP_V_4_ADDRESS);
    }

    @Test
    @Transactional
    public void createIpV4AddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ipV4AddressRepository.findAll().size();

        // Create the IpV4Address with an existing ID
        ipV4Address.setId(1L);
        IpV4AddressDTO ipV4AddressDTO = ipV4AddressMapper.toDto(ipV4Address);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIpV4AddressMockMvc.perform(post("/api/ip-v-4-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipV4AddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IpV4Address in the database
        List<IpV4Address> ipV4AddressList = ipV4AddressRepository.findAll();
        assertThat(ipV4AddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIpV4AddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = ipV4AddressRepository.findAll().size();
        // set the field null
        ipV4Address.setIpV4Address(null);

        // Create the IpV4Address, which fails.
        IpV4AddressDTO ipV4AddressDTO = ipV4AddressMapper.toDto(ipV4Address);

        restIpV4AddressMockMvc.perform(post("/api/ip-v-4-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipV4AddressDTO)))
            .andExpect(status().isBadRequest());

        List<IpV4Address> ipV4AddressList = ipV4AddressRepository.findAll();
        assertThat(ipV4AddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIpV4Addresses() throws Exception {
        // Initialize the database
        ipV4AddressRepository.saveAndFlush(ipV4Address);

        // Get all the ipV4AddressList
        restIpV4AddressMockMvc.perform(get("/api/ip-v-4-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ipV4Address.getId().intValue())))
            .andExpect(jsonPath("$.[*].ipV4Address").value(hasItem(DEFAULT_IP_V_4_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getIpV4Address() throws Exception {
        // Initialize the database
        ipV4AddressRepository.saveAndFlush(ipV4Address);

        // Get the ipV4Address
        restIpV4AddressMockMvc.perform(get("/api/ip-v-4-addresses/{id}", ipV4Address.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ipV4Address.getId().intValue()))
            .andExpect(jsonPath("$.ipV4Address").value(DEFAULT_IP_V_4_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIpV4Address() throws Exception {
        // Get the ipV4Address
        restIpV4AddressMockMvc.perform(get("/api/ip-v-4-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIpV4Address() throws Exception {
        // Initialize the database
        ipV4AddressRepository.saveAndFlush(ipV4Address);
        int databaseSizeBeforeUpdate = ipV4AddressRepository.findAll().size();

        // Update the ipV4Address
        IpV4Address updatedIpV4Address = ipV4AddressRepository.findOne(ipV4Address.getId());
        updatedIpV4Address
            .ipV4Address(UPDATED_IP_V_4_ADDRESS);
        IpV4AddressDTO ipV4AddressDTO = ipV4AddressMapper.toDto(updatedIpV4Address);

        restIpV4AddressMockMvc.perform(put("/api/ip-v-4-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipV4AddressDTO)))
            .andExpect(status().isOk());

        // Validate the IpV4Address in the database
        List<IpV4Address> ipV4AddressList = ipV4AddressRepository.findAll();
        assertThat(ipV4AddressList).hasSize(databaseSizeBeforeUpdate);
        IpV4Address testIpV4Address = ipV4AddressList.get(ipV4AddressList.size() - 1);
        assertThat(testIpV4Address.getIpV4Address()).isEqualTo(UPDATED_IP_V_4_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingIpV4Address() throws Exception {
        int databaseSizeBeforeUpdate = ipV4AddressRepository.findAll().size();

        // Create the IpV4Address
        IpV4AddressDTO ipV4AddressDTO = ipV4AddressMapper.toDto(ipV4Address);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIpV4AddressMockMvc.perform(put("/api/ip-v-4-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ipV4AddressDTO)))
            .andExpect(status().isCreated());

        // Validate the IpV4Address in the database
        List<IpV4Address> ipV4AddressList = ipV4AddressRepository.findAll();
        assertThat(ipV4AddressList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIpV4Address() throws Exception {
        // Initialize the database
        ipV4AddressRepository.saveAndFlush(ipV4Address);
        int databaseSizeBeforeDelete = ipV4AddressRepository.findAll().size();

        // Get the ipV4Address
        restIpV4AddressMockMvc.perform(delete("/api/ip-v-4-addresses/{id}", ipV4Address.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IpV4Address> ipV4AddressList = ipV4AddressRepository.findAll();
        assertThat(ipV4AddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IpV4Address.class);
        IpV4Address ipV4Address1 = new IpV4Address();
        ipV4Address1.setId(1L);
        IpV4Address ipV4Address2 = new IpV4Address();
        ipV4Address2.setId(ipV4Address1.getId());
        assertThat(ipV4Address1).isEqualTo(ipV4Address2);
        ipV4Address2.setId(2L);
        assertThat(ipV4Address1).isNotEqualTo(ipV4Address2);
        ipV4Address1.setId(null);
        assertThat(ipV4Address1).isNotEqualTo(ipV4Address2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IpV4AddressDTO.class);
        IpV4AddressDTO ipV4AddressDTO1 = new IpV4AddressDTO();
        ipV4AddressDTO1.setId(1L);
        IpV4AddressDTO ipV4AddressDTO2 = new IpV4AddressDTO();
        assertThat(ipV4AddressDTO1).isNotEqualTo(ipV4AddressDTO2);
        ipV4AddressDTO2.setId(ipV4AddressDTO1.getId());
        assertThat(ipV4AddressDTO1).isEqualTo(ipV4AddressDTO2);
        ipV4AddressDTO2.setId(2L);
        assertThat(ipV4AddressDTO1).isNotEqualTo(ipV4AddressDTO2);
        ipV4AddressDTO1.setId(null);
        assertThat(ipV4AddressDTO1).isNotEqualTo(ipV4AddressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ipV4AddressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ipV4AddressMapper.fromId(null)).isNull();
    }
}
