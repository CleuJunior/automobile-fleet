package com.automobilefleet.dao;

import com.automobilefleet.entities.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CustomerJdbcDAOTest {
    @InjectMocks
    private CostumerJdbcDAO costumerJdbcDAO;
    @Mock
    private JdbcTemplate jdbcTemplate;
    private Customer customer;

//    @BeforeAll
//    static void setup() {
//        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
//    }

//    @BeforeEach
//    void setupAttributes() {
//        this.customer = Fixture.from(Customer.class).gimme("customer");
//    }

//    @Test
//    void shouldReturnListWhenCallingFindAll()  {
//        Mockito.when(this.costumerJdbcDAO.findAll()).thenReturn(Collections.singletonList(this.customer));
//
//        final Customer actual = this.costumerJdbcDAO.findAll().stream()
//                .findFirst()
//                .orElse(null);
//
//        // Assertions
//        Assertions.assertNotNull(actual);
//        Assertions.assertEquals(this.customer.getId(), actual.getId());
//        Assertions.assertEquals(this.customer.getName(), actual.getName());
//        Assertions.assertEquals(this.customer.getBirthdate(), actual.getBirthdate());
//        Assertions.assertEquals(this.customer.getEmail(), actual.getEmail());
//        Assertions.assertEquals(this.customer.getDriverLicense(), actual.getDriverLicense());
//        Assertions.assertEquals(this.customer.getAddress(), actual.getAddress());
//        Assertions.assertEquals(this.customer.getPhone(), actual.getPhone());
//        Assertions.assertEquals(this.customer.getCreatedAt(), actual.getCreatedAt());
//        Assertions.assertEquals(this.customer.getUpdatedAt(), actual.getUpdatedAt());
//        Assertions.assertEquals(this.customer.getUpdatedAt(), actual.getUpdatedAt());
//
//    }
//
//    @Test
//    void shouldReturnCostumerAndSave() {
//        Mockito.when(this.jdbcTemplate.update(CostumerJdbcConstants.INSERT,
//                        this.customer.getName(),
//                        this.customer.getBirthdate(),
//                        this.customer.getEmail(),
//                        this.customer.getDriverLicense(),
//                        this.customer.getAddress(),
//                        this.customer.getPhone(),
//                        this.customer.getCreatedAt(),
//                        this.customer.getUpdatedAt()))
//                .thenReturn(1);
//
//        int acutal = this.costumerJdbcDAO.save(this.customer);
//        Assertions.assertEquals(1, acutal);
//    }
}