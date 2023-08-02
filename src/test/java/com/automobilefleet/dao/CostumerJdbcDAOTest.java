package com.automobilefleet.dao;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.entities.Costumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CostumerJdbcDAOTest {
    @InjectMocks
    private CostumerJdbcDAO costumerJdbcDAO;
    @Mock
    private JdbcTemplate jdbcTemplate;
    private Costumer costumer;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.costumer = Fixture.from(Costumer.class).gimme("costumer");
    }

    @Test
    void shouldReturnListWhenCallingFindAll()  {
        Mockito.when(this.costumerJdbcDAO.findAll()).thenReturn(Collections.singletonList(this.costumer));

        final Costumer actual = this.costumerJdbcDAO.findAll().stream()
                .findFirst()
                .orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.costumer.getId(), actual.getId());
        Assertions.assertEquals(this.costumer.getName(), actual.getName());
        Assertions.assertEquals(this.costumer.getBirthdate(), actual.getBirthdate());
        Assertions.assertEquals(this.costumer.getEmail(), actual.getEmail());
        Assertions.assertEquals(this.costumer.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(this.costumer.getAddress(), actual.getAddress());
        Assertions.assertEquals(this.costumer.getPhone(), actual.getPhone());
        Assertions.assertEquals(this.costumer.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(this.costumer.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(this.costumer.getUpdatedAt(), actual.getUpdatedAt());

    }

    @Test
    void shouldReturnCostumerAndSave() {
        Mockito.when(this.jdbcTemplate.update(CostumerJdbcConstants.INSERT,
                        this.costumer.getName(),
                        this.costumer.getBirthdate(),
                        this.costumer.getEmail(),
                        this.costumer.getDriverLicense(),
                        this.costumer.getAddress(),
                        this.costumer.getPhone(),
                        this.costumer.getCreatedAt(),
                        this.costumer.getUpdatedAt()))
                .thenReturn(1);

        int acutal = this.costumerJdbcDAO.save(this.costumer);
        Assertions.assertEquals(1, acutal);
    }
}