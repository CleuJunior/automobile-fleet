package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.SpecificationBuilder.specificationRespnseBuilder;
import static com.automobilefleet.builder.SpecificationBuilder.specificationdBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class SpecificationMapperTest {

    @InjectMocks
    private SpecificationMapper mapper;

    private Specification specification;
    private SpecificationResponse response;

    @BeforeEach
    void setUp() {
        specification = specificationdBuilder();
        response = specificationRespnseBuilder(specification);
    }

    @Test
    void shouldMapSpecificationToSpecificationResponse() {
        var result = mapper.toSpecificationResponse(specification);

        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapSpecificationListToSpecificationResponseList() {
        var result = mapper.toSpecificationResponseList(singletonList(specification));

        then(result).isNotEmpty();
        then(result).contains(response);
    }

    @Test
    void shouldMapSpecificationPageSpecificationResponsePage() {
        var page = new PageImpl<>(singletonList(specification));

        var result = mapper.toSpecificationResponsePage(page, 0, 1);

        then(result).isNotEmpty();
        then(result.getTotalElements()).isEqualTo(1);
        then(result.getContent()).contains(response);
    }

    @Test
    void shouldApplySpecificationUpdates() {
        var update = mock(SpecificationRequest.class);
        var result = mapper.apply(specification, update);

        then(result).isEqualTo(specification);
    }
}