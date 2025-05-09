package integrationTest.api;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import integrationTest.api.config.AbstractWebIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static integrationTest.api.data.DataIT.CATEGORY_COUPE_ID;
import static integrationTest.api.data.DataIT.CATEGORY_MINIVANS_DESCRIPTION;
import static integrationTest.api.data.DataIT.CATEGORY_MINIVANS_ID;
import static integrationTest.api.data.DataIT.CATEGORY_MINIVANS_NAME;
import static integrationTest.api.data.DataIT.CATEGORY_OLD_CARS_ID;
import static integrationTest.api.data.DataIT.CATEGORY_SUVs_ID;
import static integrationTest.api.fixture.CategoryFixture.getCategoryById;
import static integrationTest.api.fixture.CategoryFixture.getCategoryList;
import static integrationTest.api.fixture.CategoryFixture.postCategory;
import static integrationTest.api.fixture.CategoryFixture.putCategory;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

class CategoryControllerIT extends AbstractWebIntegrationTest {


    @Test
    void shouldCategoryByIdAndStatusCodeOK() {
        getCategoryById(CATEGORY_MINIVANS_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", is(CATEGORY_MINIVANS_ID))
                .body("name", is(CATEGORY_MINIVANS_NAME))
                .body("description", is(CATEGORY_MINIVANS_DESCRIPTION));
    }

    @Test
    void shouldGetListCategoryAndStatusCodeOK() {
        getCategoryList()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("_id", hasItem(CATEGORY_SUVs_ID))
                .body("_id", hasItem(CATEGORY_OLD_CARS_ID));
    }

    @Test
    void shouldSaveCategoryAndStatusCodeCreated() {
        var name = faker.leagueOfLegends().champion();
        var description = faker.leagueOfLegends().quote();
        var request = new CategoryRequest(name, description);

        var response = postCategory(request)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CategoryResponse.class);

        then(response.id()).isNotNull();
        then(response.name()).isEqualTo(name);
        then(response.description()).isEqualTo(description);
        then(response.createdAt()).isNotNull();
    }

    @Test
    void shouldUpdateBrandAndStatusCodeAccepted() {
        var name = faker.harryPotter().character();
        var description = faker.harryPotter().quote();
        var request = new CategoryRequest(name, description);

        var response = putCategory(CATEGORY_COUPE_ID, request)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(CategoryResponse.class);

        then(response.id().toString()).isEqualTo(CATEGORY_COUPE_ID);
        then(response.name()).isEqualTo(request.name());
        then(response.description()).isEqualTo(request.description());
        then(response.createdAt()).isNotNull();
    }
}