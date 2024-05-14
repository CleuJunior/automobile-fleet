package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.CategoryBuilder.categoryBuilder;
import static com.automobilefleet.builder.CategoryBuilder.categoryResponseBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
class CategoryMapperTest {

    @InjectMocks
    private CategoryMapper mapper;

    private Category category;
    private CategoryResponse response;

    @BeforeEach
    void setUp() {
        category = categoryBuilder();
        response = categoryResponseBuilder(category);
    }

    @Test
    void shouldMapCategoryToCategoryResponse() {
        var result = mapper.toCategoryResponse(category);

        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapCategoryListToCategoryResponseList() {
        var result = mapper.toCategoryResponseList(singletonList(category));

        then(result).isNotEmpty();
        then(result).contains(response);
    }

    @Test
    void shouldMapCategoryPageToCategoryResponsePage() {
        var page = new PageImpl<>(singletonList(category));

        var result = mapper.toCategoryResponsePage(page, 0, 1);

        then(result).isNotEmpty();
        then(result.getTotalElements()).isEqualTo(1);
        then(result.getContent()).contains(response);
    }

}