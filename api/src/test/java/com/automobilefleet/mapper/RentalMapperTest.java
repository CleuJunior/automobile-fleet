package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.response.RentalResponse;
import com.automobilefleet.entities.Rental;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.RentalBuilder.rentalBuilder;
import static com.automobilefleet.builder.RentalBuilder.rentalRespnseBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class RentalMapperTest {

    @Mock
    private CarMapper carMapper;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private RentalMapper mapper;

    private Rental rental;
    private RentalResponse response;

    @BeforeEach
    void setUp() {
        rental = rentalBuilder();
        response = rentalRespnseBuilder(rental);

        given(carMapper.toCarResponse(rental.getCar())).willReturn(response.car());
        given(customerMapper.toCustomerResponse(rental.getCustomer())).willReturn(response.customer());
    }

    @AfterEach
    void tearDown() {
        verify(carMapper).toCarResponse(rental.getCar());
        verify(customerMapper).toCustomerResponse(rental.getCustomer());
    }

    @Test
    void shouldMapRentalToRentalResponse() {
        var result = mapper.toRentalResponse(rental);

        then(result).isNotNull();
        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapRentalListToRentalResponseList() {
        var result = mapper.toRentalResponseList(singletonList(rental));

        then(result).isNotEmpty();
        then(result).contains(response);
    }
}