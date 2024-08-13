package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.CustomerRequest;
import com.automobilefleet.api.dto.response.CustomerResponse;
import com.automobilefleet.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.automobilefleet.builder.CustomerBuilder.customerBuilder;
import static com.automobilefleet.builder.CustomerBuilder.customerRespnseBuilder;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class CustomerMapperTest {

    @InjectMocks
    private CustomerMapper customerMapper;

    private Customer customer;
    private CustomerResponse response;

    @BeforeEach
    void setUp() {
        customer = customerBuilder();
        response = customerRespnseBuilder(customer);
    }

    @Test
    void shouldMapCustomerToCustomerResponse() {
        var result = customerMapper.toCustomerResponse(customer);

        then(result).isNotNull();
        then(result).isEqualTo(response);
    }

    @Test
    void shouldMapCustomerListToCustomerResponseList() {
        var result = customerMapper.toCustomerResponseList(singletonList(customer));

        then(result).isNotEmpty();
        then(result).contains(response);
    }

    @Test
    void shouldMapCustomerPageToCustomerResponsePage() {
        var page = new PageImpl<>(singletonList(customer));
        var result = customerMapper.toCustomerResponsePage(page, 0, 1);

        then(result).isNotEmpty();
        then(result.getTotalElements()).isEqualTo(1);
        then(result.getContent()).contains(response);
    }

    @Test
    void shouldApplyCustomerUpdates() {
        var update = mock(CustomerRequest.class);
        var result = customerMapper.apply(customer, update);

        then(result).isEqualTo(customer);
    }
}