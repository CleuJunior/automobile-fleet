package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.BrandBuilder.brandResponseBuilder;
import static com.automobilefleet.builder.CarBuilder.carBuilder;
import static com.automobilefleet.builder.CarBuilder.carResponseBuilder;
import static com.automobilefleet.builder.CategoryBuilder.categoryResponseBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CarMapperTest {

    private Car car;
    private CarResponse response;
    private Brand brand;
    private Category category;
    @Mock
    private CategoryMapper categoryMapper;
    @Mock
    private BrandMapper brandMapper;
    @InjectMocks
    private CarMapper mapper;

    @BeforeEach
    void setUp() {
        car = carBuilder();
        brand = car.getBrand();
        category = car.getCategory();

        response = carResponseBuilder(car);
        var brandResponse = brandResponseBuilder(brand);
        var categoryResponse = categoryResponseBuilder(category);

        given(brandMapper.toBrandResponse(brand)).willReturn(brandResponse);
        given(categoryMapper.toCategoryResponse(category)).willReturn(categoryResponse);
    }

    @Test
    void shouldMapCarToCarResponse() {
        var result = mapper.toCarResponse(car);

        then(result).isNotNull();
        then(result).isEqualTo(response);
        then(result).isInstanceOf(CarResponse.class);

        verify(brandMapper).toBrandResponse(brand);
        verify(categoryMapper).toCategoryResponse(category);
    }

    @Test
    void shouldMapBrandListToBrandResponseList() {
        var result = mapper.toCarResponseList(singletonList(car));

        then(result).isNotEmpty();
        then(result).contains(response);

        verify(brandMapper).toBrandResponse(brand);
        verify(categoryMapper).toCategoryResponse(category);
    }

    @Test
    void shouldMapBrandListToBrandResponsePage() {
        var page = new PageImpl<>(singletonList(car));

        var result = mapper.toCarResponsePage(page, 0, 1);

        then(result).isNotEmpty();
        then(result.getTotalElements()).isEqualTo(1);
        then(result.getContent()).contains(response);

        verify(brandMapper).toBrandResponse(brand);
        verify(categoryMapper).toCategoryResponse(category);
    }

    @Test
    void shouldApplyCarUpdates() {
        var request = mock(CarRequest.class);
        var result = mapper.apply(car, request, brand, category);

        then(result).isEqualTo(car);
    }
}