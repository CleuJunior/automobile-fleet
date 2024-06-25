package integrationTest.api;

import com.automobilefleet.api.dto.request.BrandRequest;
import integrationTest.api.config.AbstractWebIntegrationTest;
import integrationTest.api.data.RandonData;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static integrationTest.api.config.Status.ACCEPTED;
import static integrationTest.api.config.Status.CREATED;
import static integrationTest.api.config.Status.OK;
import static integrationTest.api.data.DataIT.BMW_ID;
import static integrationTest.api.data.DataIT.BRAND_BMW;
import static integrationTest.api.data.DataIT.BRAND_CHEVROLET;
import static integrationTest.api.data.DataIT.BRAND_FERRARI;
import static integrationTest.api.data.DataIT.BRAND_FORD;
import static integrationTest.api.data.DataIT.BRAND_HONDA;
import static integrationTest.api.data.DataIT.BRAND_NISSAN;
import static integrationTest.api.data.DataIT.BRAND_RENAULT;
import static integrationTest.api.data.DataIT.BRAND_TOYOTA;
import static integrationTest.api.data.DataIT.BRAND_VOLKSWAGEN;
import static integrationTest.api.data.DataIT.BRAND_YAMAHA;
import static integrationTest.api.data.DataIT.CHEVROLET_ID;
import static integrationTest.api.data.DataIT.FERRARI_ID;
import static integrationTest.api.data.DataIT.FORD_ID;
import static integrationTest.api.data.DataIT.HONDA_ID;
import static integrationTest.api.data.DataIT.NISSAN_ID;
import static integrationTest.api.data.DataIT.RENAULT_ID;
import static integrationTest.api.data.DataIT.TOYOTA_ID;
import static integrationTest.api.data.DataIT.VOLKSWAGEN_ID;
import static integrationTest.api.data.DataIT.YAMAHA_ID;
import static integrationTest.api.fixture.BrandFixture.getBrandById;
import static integrationTest.api.fixture.BrandFixture.getBrandList;
import static integrationTest.api.fixture.BrandFixture.getBrandPaged;
import static integrationTest.api.fixture.BrandFixture.postBrand;
import static integrationTest.api.fixture.BrandFixture.putBrand;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

//@ActiveProfiles("test")
@Transactional
@Rollback
class BrandControllerIT extends AbstractWebIntegrationTest {

    @Test
    void shouldGetBrandByIdAndStatusCodeOK() {
        getBrandById(BMW_ID)
                .then()
                .statusCode(OK)
                .body("_id", is(BMW_ID))
                .body("name", is(BRAND_BMW));
    }

    @Test
    void shouldGetListBrandAndStatusCodeOK() {
        getBrandList()
                .then()
                .statusCode(OK)
                .body("_id", hasItems(BMW_ID, CHEVROLET_ID))
                .body("_id", hasItems(YAMAHA_ID, RENAULT_ID))
                .body("_id", hasItems(NISSAN_ID, TOYOTA_ID))
                .body("_id", hasItems(FORD_ID, VOLKSWAGEN_ID))
                .body("_id", hasItems(FERRARI_ID, HONDA_ID))
                .body("name", hasItems(BRAND_BMW, BRAND_CHEVROLET))
                .body("name", hasItems(BRAND_YAMAHA, BRAND_RENAULT))
                .body("name", hasItems(BRAND_NISSAN, BRAND_TOYOTA))
                .body("name", hasItems(BRAND_FORD, BRAND_VOLKSWAGEN))
                .body("name", hasItems(BRAND_FERRARI, BRAND_HONDA));
    }

    @Test
    void shouldGetPagetBrandAndStatusCodeOK() {
        getBrandPaged(1, 3)
                .then()
                .statusCode(OK)
                .log().all()
                .body("content._id", hasItems(RENAULT_ID, NISSAN_ID, TOYOTA_ID))
                .body("pageable.pageNumber", is(1))
                .body("pageable.pageSize", is(3));
    }

    @Test
    void shoulSaveBrandAndStatusCodeCreated() {
        var name = RandonData.name();
        var request = new BrandRequest(name);

        var brandId = postBrand(request)
                .then()
                .statusCode(CREATED)
                .extract()
                .path("_id")
                .toString();

        getBrandById(brandId)
                .then()
                .statusCode(OK)
                .body("_id", is(brandId))
                .body("name", is(name));
    }

    @Test
    void shouldUpdateBrandAndStatusCodeAccepted() {
        var name = RandonData.name();
        var request = new BrandRequest(name);

        putBrand(BMW_ID, request)
                .then()
                .statusCode(ACCEPTED)
                .body("_id", is(BMW_ID))
                .body("name", is(name));

    }
}