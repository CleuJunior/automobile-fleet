package com.automobilefleet.integrationTest;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.automobilefleet.integrationTest.DataIT.CREATED_AT_TEN;
import static com.automobilefleet.integrationTest.DataIT.FERNANDO_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.FERNANDO_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.FERNANDO_ID;
import static com.automobilefleet.integrationTest.DataIT.GUSTAVO_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.GUSTAVO_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.GUSTAVO_ID;
import static com.automobilefleet.integrationTest.DataIT.HELOISA_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.HELOISA_DRIVER_LICENSE;
import static com.automobilefleet.integrationTest.DataIT.HELOISA_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.HELOISA_ID;
import static com.automobilefleet.integrationTest.DataIT.HELOISA_PHONE_NUMBER;
import static com.automobilefleet.integrationTest.DataIT.HUGO_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.HUGO_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.HUGO_ID;
import static com.automobilefleet.integrationTest.DataIT.JULIO_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.JULIO_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.JULIO_ID;
import static com.automobilefleet.integrationTest.DataIT.LUANA_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.LUANA_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.LUANA_ID;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_ADDRESS;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_BIRTHDATE;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_DRIVER_LICENSE;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_ID;
import static com.automobilefleet.integrationTest.DataIT.MARCELA_PHONE_NUMBER;
import static com.automobilefleet.integrationTest.DataIT.MARTA_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.MARTA_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.MARTA_ID;
import static com.automobilefleet.integrationTest.DataIT.PEDRO_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.PEDRO_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.PEDRO_ID;
import static com.automobilefleet.integrationTest.DataIT.RAIMUNDA_CUSTOMER;
import static com.automobilefleet.integrationTest.DataIT.RAIMUNDA_EMAIL;
import static com.automobilefleet.integrationTest.DataIT.RAIMUNDA_ID;
import static com.automobilefleet.integrationTest.DataIT.UPDATED_AT_TEN;
import static com.automobilefleet.util.DateUtils.dateFormatToString;
import static com.automobilefleet.util.DateUtils.localDateFromDate;
import static com.automobilefleet.utils.JsonMapper.asJsonString;
import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@Transactional
class CustomerControllerIT extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final static Faker faker = new Faker();
    private final static String ENDPOINT = "/api/v1/customer";
    private final static String ENDPOINT_ID = ENDPOINT + "/{id}";

    @Test
    void shouldGetCustomerdByIdAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT_ID, MARCELA_ID).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(MARCELA_ID))
                .andExpect(jsonPath("$.name").value(MARCELA_CUSTOMER))
                .andExpect(jsonPath("$.birth_date").value(MARCELA_BIRTHDATE))
                .andExpect(jsonPath("$.email").value(MARCELA_EMAIL))
                .andExpect(jsonPath("$.driver_license").value(MARCELA_DRIVER_LICENSE))
                .andExpect(jsonPath("$.address").value(MARCELA_ADDRESS))
                .andExpect(jsonPath("$.phone_number").value(MARCELA_PHONE_NUMBER))
                .andExpect(jsonPath("$.created_at").value(CREATED_AT_TEN))
                .andExpect(jsonPath("$.updated_at").value(UPDATED_AT_TEN));
    }

    @Test
    void shouldGetListCustomersAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*]._id").value(containsInAnyOrder(RAIMUNDA_ID, GUSTAVO_ID, HELOISA_ID, JULIO_ID, HUGO_ID, MARTA_ID, PEDRO_ID, LUANA_ID, FERNANDO_ID, MARCELA_ID)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(RAIMUNDA_CUSTOMER, GUSTAVO_CUSTOMER, HELOISA_CUSTOMER, JULIO_CUSTOMER, HUGO_CUSTOMER, MARTA_CUSTOMER, PEDRO_CUSTOMER, LUANA_CUSTOMER, FERNANDO_CUSTOMER, MARCELA_CUSTOMER)))
                .andExpect(jsonPath("$[*].email").value(containsInAnyOrder(RAIMUNDA_EMAIL, GUSTAVO_EMAIL, HELOISA_EMAIL, JULIO_EMAIL, HUGO_EMAIL, MARTA_EMAIL, PEDRO_EMAIL, LUANA_EMAIL, FERNANDO_EMAIL, MARCELA_EMAIL)));
    }

    @Test
    void shouldGetPagetCustomerAndStatusCodeOK() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .param("page", "0")
                        .param("size", "2")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[*]._id").value(containsInAnyOrder(RAIMUNDA_ID, GUSTAVO_ID)))
                .andExpect(jsonPath("$.content[*].name").value(containsInAnyOrder(RAIMUNDA_CUSTOMER, GUSTAVO_CUSTOMER)))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(5));
    }

    @Test
    void shoulSaveCustomerAndStatusCodeCreated() throws Exception {
        var name = faker.harryPotter().character();
        var birthdate = localDateFromDate(faker.date().birthday());
        var dateString = dateFormatToString(birthdate);
        var email = "test_input_user@gmail.com";
        var driverLicense = valueOf(faker.number().numberBetween(10000000000L, 99999999999L));
        var address = faker.harryPotter().location();
        var phone = "(31) 51557-8143";

        var request = new CustomerRequest(name, birthdate, email, driverLicense, address, phone);

        mockMvc.perform(post(ENDPOINT).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.birth_date").value(dateString))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.driver_license").value(driverLicense))
                .andExpect(jsonPath("$.address").value(address))
                .andExpect(jsonPath("$.phone_number").value(phone))
                .andExpect(jsonPath("$.created_at").isNotEmpty());
    }

    @Test
    void shouldUpdateCustomerAndStatusCodeAccepted() throws Exception {
        var name = faker.leagueOfLegends().champion();
        var birthdate = localDateFromDate(faker.date().birthday());
        var dateString = dateFormatToString(birthdate);
        var address = faker.leagueOfLegends().location();

        var request = new CustomerRequest(name, birthdate, HELOISA_EMAIL, HELOISA_DRIVER_LICENSE, address, HELOISA_PHONE_NUMBER);

        mockMvc.perform(put(ENDPOINT_ID, HELOISA_ID).contentType(APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$._id").value(HELOISA_ID))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.birth_date").value(dateString))
                .andExpect(jsonPath("$.email").value(HELOISA_EMAIL))
                .andExpect(jsonPath("$.driver_license").value(HELOISA_DRIVER_LICENSE))
                .andExpect(jsonPath("$.address").value(address))
                .andExpect(jsonPath("$.phone_number").value(HELOISA_PHONE_NUMBER));
    }
//
//    @Test
//    void shouldDeleteBrandAndStatusCodeNoContent() throws Exception {
//        mockMvc.perform(delete(DELETE_ID, ID).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent())
//                .andReturn();
//
//        Mockito.verify(service).deleteBrandById(ID);
//        Mockito.verifyNoMoreInteractions(service);
//    }
}