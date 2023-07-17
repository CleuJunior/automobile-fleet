package com.automobilefleet.dao;

import com.automobilefleet.entities.Costumer;
import com.automobilefleet.utils.costumer.CostumerFactoryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.util.Assert;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CostumerJdbcDAOTest {
    @InjectMocks
    private CostumerJdbcDAO costumerJdbcDAO;
    @Mock
    private JdbcTemplate jdbcTemplate;
    private Costumer costumer;

    @BeforeEach
    void setUp() {
        this.costumer = CostumerFactoryUtils.costumerBuilRaimunda();
    }

    @Test
    void shouldReturnListWhenCallingFindAll()  {
        final List<Costumer> costumers = List.of(
                CostumerFactoryUtils.costumerBuilRaimunda(),
                CostumerFactoryUtils.costumerBuildGustavo(),
                CostumerFactoryUtils.costumerBuildMaercela()
        );

        Mockito.when(this.costumerJdbcDAO.findAll()).thenReturn(costumers);
        List<Costumer> actual = this.costumerJdbcDAO.findAll();

        // Assert Null, Empty and Size
        Assertions.assertNotNull(actual);
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(3, actual.size());

        // Assert same Object
        this.assertionsDatas(costumers.get(0), actual.get(0));
        this.assertionsDatas(costumers.get(1), actual.get(1));
        this.assertionsDatas(costumers.get(2), actual.get(2));
    }

    private void assertionsDatas(Costumer expected, Costumer actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
        Assertions.assertEquals(expected.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(expected.getAddress(), actual.getAddress());
        Assertions.assertEquals(expected.getPhone(), actual.getPhone());
        Assertions.assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(expected.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    void shouldReturnCostumerAndSave() {
        Mockito.when(this.jdbcTemplate.update(CostumerJdbcConstants.QUERY_INSERT,
                        this.costumer.getName(),
                        this.costumer.getBirthDate(),
                        this.costumer.getEmail(),
                        this.costumer.getDriverLicense(),
                        this.costumer.getAddress(),
                        this.costumer.getPhone(),
                        this.costumer.getCreatedAt(),
                        this.costumer.getUpdatedAt()))
                .thenReturn(1);

        int acutal = this.costumerJdbcDAO.save(CostumerFactoryUtils.costumerBuilRaimunda());
        Assertions.assertEquals(1, acutal);
    }
}