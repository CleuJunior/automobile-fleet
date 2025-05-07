package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CarSpecificationResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.CarSpecification;
import com.automobilefleet.entities.Specification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.CarBuilder.carResponseBuilder;
import static com.automobilefleet.builder.CarSpecificationBuilder.carSpecificationBuilder;
import static com.automobilefleet.builder.CarSpecificationBuilder.carSpecificationRespnseBuilder;
import static com.automobilefleet.builder.SpecificationBuilder.specificationRespnseBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CarSpecificationMapperTest {

    @Mock
    private CarMapper carMapper;
    @Mock
    private SpecificationMapper specificationMapper;
    @InjectMocks
    private CarSpecificationMapper mapper;

    private CarSpecification carSpecification;
    private CarSpecificationResponse response;

    @BeforeEach
    void setUp() {
        carSpecification = carSpecificationBuilder();
        response = carSpecificationRespnseBuilder(carSpecification);
        var carResponse = carResponseBuilder(carSpecification.getCar());
        var specificationResponse = specificationRespnseBuilder(carSpecification.getSpecification());

        given(carMapper.toCarResponse(carSpecification.getCar())).willReturn(carResponse);
        given(specificationMapper.toSpecificationResponse(carSpecification.getSpecification())).willReturn(specificationResponse);
    }

    @Test
    void shouldMapToCarSpecificationResponse() {
        var result = mapper.toCarSpecificationResponse(carSpecification);

        then(result).isEqualTo(response);

        verify(carMapper).toCarResponse(carSpecification.getCar());
        verify(specificationMapper).toSpecificationResponse(carSpecification.getSpecification());
    }

    @Test
    void shouldMapCarSpecificationListToCarSpecificationResponseList() {
        var result = mapper.toCarSpecificationResponseList(singletonList(carSpecification));

        then(result).isNotEmpty();
        then(result).contains(response);

        verify(carMapper).toCarResponse(carSpecification.getCar());
        verify(specificationMapper).toSpecificationResponse(carSpecification.getSpecification());
    }

    @Test
    void shouldApplyCarSpecificationUpdate() {
        var car = mock(Car.class);
        var specification = mock(Specification.class);
        var result = mapper.apply(carSpecification, car, specification);

        then(result).isEqualTo(carSpecification);
    }
}