package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
class BrandMapperTest {

    @Mock
    private Brand brand;
    @Mock
    private BrandResponse response;
    @Mock
    private BrandRequest request;
    @InjectMocks
    private BrandMapper mapper;

    @Test
    void shouldMapBrandToBrandResponse() {
        var result = mapper.toBrandResponse(brand);

        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapBrandListToBrandResponseList() {
        var result = mapper.toListBrandResponse(singletonList(brand));

        then(result).isNotEmpty();
        then(result).contains(response);
    }

    @Test
    void shouldMapBrandListToBrandResponsePage() {
        var page = new PageImpl<>(singletonList(brand));

        var result = mapper.toBrandResponsePage(page, 0, 1);

        then(result).isNotEmpty();
        then(result.getTotalElements()).isEqualTo(1);
        then(result.getContent()).contains(response);
    }

    @Test
    void shouldApplyBrandUpdates() {
        var result = mapper.apply(brand, request);
        then(result).isEqualTo(brand);
    }
}