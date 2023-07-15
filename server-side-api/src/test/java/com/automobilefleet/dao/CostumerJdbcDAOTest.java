package com.automobilefleet.dao;

import com.automobilefleet.entities.Costumer;
import com.automobilefleet.services.CostumerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CostumerJdbcDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(CostumerService.class);

    @InjectMocks
    private CostumerJdbcDAO costumerJdbcDAO;
    @Mock
    private JdbcTemplate jdbcTemplate;
    private Costumer costumer;
    private static final long NON_EXISTING_ID = 99L;
    private static final long ID = 1L;
    private static final String NAME = "John Doe";
    private static final LocalDate BIRTH_DATE = LocalDate.of(1988, 12, 30);
    private static final String EMAIL = "johndoe@outlook.com";
    private static final String DRIVE_LICENSE = "51530697720";
    private static final String ADDRESS = "Street test, 00";
    private static final String PHONE = "(98) 3789-7445";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2022, 9, 11, 9, 31, 23);
    private static final LocalDateTime UPDATE_AT = LocalDateTime.of(2022, 9, 14, 9, 31, 23);

    @BeforeEach
    void setUp() {
        this.costumer = new Costumer(ID, NAME, BIRTH_DATE, EMAIL, DRIVE_LICENSE, ADDRESS, PHONE, CREATED_AT, UPDATE_AT);
    }

    @Test
    void shouldReturnListWhenCallingFindAll()  {
        Mockito.when(this.costumerJdbcDAO.findAll()).thenReturn(List.of(this.costumer));
        List<Costumer> actual = this.costumerJdbcDAO.findAll();

        // Assert Null, Empty and Size
        Assertions.assertNotNull(actual);
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(1, actual.size());

        // Assert same Object
        Assertions.assertSame(this.costumer.getId(), actual.get(0).getId());
        Assertions.assertSame(this.costumer.getName(), actual.get(0).getName());
        Assertions.assertSame(this.costumer, actual.get(0));

        LOG.info("findAll() passed!");
    }
}