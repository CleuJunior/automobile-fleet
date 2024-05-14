package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.CarImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.CarBuilder.carResponseBuilder;
import static com.automobilefleet.builder.CarImageBuilder.carImageBuilder;
import static com.automobilefleet.builder.CarImageBuilder.carImageResponseBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CarImageMapperTest {

    @Mock
    private CarMapper carMapper;
    @InjectMocks
    private CarImageMapper mapper;

    private CarImage image;
    private CarImageResponse response;

    @BeforeEach
    void setUp() {
        image = carImageBuilder();
        response = carImageResponseBuilder(image);

        given(carMapper.toCarResponse(image.getCar())).willReturn(carResponseBuilder(image.getCar()));
    }

    @AfterEach
    void tearDown() {
        verify(carMapper).toCarResponse(image.getCar());
    }

    @Test
    void shoulMapToCarImageResponse() {
        var result = mapper.toCarImageResponse(image);

        then(result).isNotNull();
        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapCarImageListToCarImageResponseList() {
        var result = mapper.toCarImageResponseList(singletonList(image));

        then(result).isNotEmpty();
        then(result).contains(response);
    }

    @Test
    void shouldMapCarImagePageCarImageResponsePage() {
        var page = new PageImpl<>(singletonList(image));

        var result = mapper.toCarImageResponsePage(page, 0, 10);

        then(result).isNotEmpty();
        then(result.getTotalElements()).isEqualTo(1);
        then(result.getContent()).contains(response);
    }
}