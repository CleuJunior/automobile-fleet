package integrationTest.api;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;

import static com.automobilefleet.util.DateUtils.localDateFromDate;
import static integrationTest.api.data.DataIT.CREATED_AT_THREE;
import static integrationTest.api.data.DataIT.FERNANDO_CUSTOMER;
import static integrationTest.api.data.DataIT.FERNANDO_ID;
import static integrationTest.api.data.DataIT.GUSTAVO_ID;
import static integrationTest.api.data.DataIT.HELOISA_ADDRESS;
import static integrationTest.api.data.DataIT.HELOISA_BIRTHDATE;
import static integrationTest.api.data.DataIT.HELOISA_CUSTOMER;
import static integrationTest.api.data.DataIT.HELOISA_DRIVER_LICENSE;
import static integrationTest.api.data.DataIT.HELOISA_EMAIL;
import static integrationTest.api.data.DataIT.HELOISA_ID;
import static integrationTest.api.data.DataIT.HELOISA_PHONE_NUMBER;
import static integrationTest.api.data.DataIT.HUGO_CUSTOMER;
import static integrationTest.api.data.DataIT.HUGO_ID;
import static integrationTest.api.data.DataIT.RAIMUNDA_ID;
import static integrationTest.api.data.DataIT.UPDATED_AT_THREE;
import static integrationTest.api.fixture.CustomerFixture.deleteCustomer;
import static integrationTest.api.fixture.CustomerFixture.getCustomerById;
import static integrationTest.api.fixture.CustomerFixture.getCustomerList;
import static integrationTest.api.fixture.CustomerFixture.getCustomerPaged;
import static integrationTest.api.fixture.CustomerFixture.postCustomer;
import static integrationTest.api.fixture.CustomerFixture.putCustomer;
import static java.lang.String.valueOf;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

class CustomerControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetCustomerByIdAndStatusCodeOK() {
        getCustomerById(HELOISA_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(HELOISA_ID))
                .body("name", is(HELOISA_CUSTOMER))
                .body("birth_date", is(HELOISA_BIRTHDATE))
                .body("email", is(HELOISA_EMAIL))
                .body("driver_license", is(HELOISA_DRIVER_LICENSE))
                .body("address", is(HELOISA_ADDRESS))
                .body("phone_number", is(HELOISA_PHONE_NUMBER))
                .body("created_at", is(CREATED_AT_THREE))
                .body("updated_at", is(UPDATED_AT_THREE));
    }

    @Test
    void shouldGetListCustomersAndStatusCodeOK() {
        getCustomerList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItems(HELOISA_ID))
                .body("_id", hasItems(HUGO_ID))
                .body("_id", hasItems(FERNANDO_ID))
                .body("name", hasItems(HELOISA_CUSTOMER))
                .body("name", hasItems(HUGO_CUSTOMER))
                .body("name", hasItems(FERNANDO_CUSTOMER));
    }

    @Test
    void shouldGetPagedCustomerAndStatusCodeOK() {
        getCustomerPaged(0, 2)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content._id", hasItems(RAIMUNDA_ID, GUSTAVO_ID))
                .body("pageable.pageNumber", is(0))
                .body("pageable.pageSize", is(2));
    }

    @Test
    @Rollback
    void shouldSaveCustomerAndStatusCodeCreated() {
        var name = faker.harryPotter().character();
        var birthdate = localDateFromDate(faker.date().birthday());
        var email = faker.internet().emailAddress();
        var driverLicense = valueOf(faker.number().numberBetween(10000000000L, 99999999999L));
        var address = faker.address().fullAddress();
        var phone = "(31) 51557-8143";
        var request = new CustomerRequest(name, birthdate, email, driverLicense, address, phone);

        var response = postCustomer(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CustomerResponse.class);

        then(response.id()).isNotNull();
        then(response.name()).isEqualTo(name);
        then(response.birthdate()).isEqualTo(birthdate);
        then(response.email()).isEqualTo(email);
        then(response.driverLicense()).isEqualTo(driverLicense);
        then(response.address()).isEqualTo(address);
        then(response.phone()).isEqualTo(phone);
        then(response.createdAt()).isNotNull();
        then(response.updatedAt()).isNotNull();
    }

    @Test
    @Rollback
    void shouldUpdateCustomerAndStatusCodeAccepted() {
        var name = faker.leagueOfLegends().champion();
        var birthdate = localDateFromDate(faker.date().birthday());
        var email = faker.internet().emailAddress();
        var driverLicense = valueOf(faker.number().numberBetween(10000000000L, 99999999999L));
        var address = faker.leagueOfLegends().location();
        var phone = "(31) 51557-8143";

        var request = new CustomerRequest(name, birthdate, email, driverLicense, address, phone);

        var response = putCustomer(HELOISA_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(CustomerResponse.class);

        then(response.id().toString()).isEqualTo(HELOISA_ID);
        then(response.name()).isEqualTo(name);
        then(response.birthdate()).isEqualTo(birthdate);
        then(response.email()).isEqualTo(email);
        then(response.driverLicense()).isEqualTo(driverLicense);
        then(response.address()).isEqualTo(address);
        then(response.phone()).isEqualTo(phone);
    }
}