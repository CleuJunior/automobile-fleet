package com.automobilefleet.services;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.utils.FactoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CostumerServiceTest  {
    @InjectMocks
    private CostumerService costumerService;
    @Mock
    private CostumerRepository repository;
    @Mock
    private ModelMapper mapper;
    private Costumer costumer;
    private CostumerRequest request;
    private CostumerResponse reponse;
    private static final long ID = 1L;

    @BeforeEach
    void setUp() {
        this.costumer = FactoryUtils.createCostumer();
        this.reponse = FactoryUtils.createCostumerResponse();
        this.request = FactoryUtils.createCostumerRequest();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAListWithThreeElement() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.reponse);

        final CostumerResponse actual = this.costumerService.listCostumer()
                .stream()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(actual);
        Assertions.assertSame(this.reponse, actual);

        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(this.reponse.getEmail(), actual.getEmail());
        Assertions.assertEquals(this.reponse.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(this.reponse.getAddress(), actual.getAddress());
        Assertions.assertEquals(this.reponse.getPhone(), actual.getPhone());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnOneCostumerById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.ofNullable(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.reponse);

        final CostumerResponse actual =  this.costumerService.getCostumerById(ID);

        // Assertions
        Assertions.assertNotNull(actual);

        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));

        Assertions.assertInstanceOf(CostumerResponse.class, actual);

        Assertions.assertSame(this.reponse, actual);

        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(this.reponse.getEmail(), actual.getEmail());
        Assertions.assertEquals(this.reponse.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(this.reponse.getAddress(), actual.getAddress());
        Assertions.assertEquals(this.reponse.getPhone(), actual.getPhone());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldThrowsCostumerNotFoundExceptionWhenIdNonExisting() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.costumerService.getCostumerById(ID));

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldSaveCostumerWhenCallingSaveCostumer() {
        Mockito.when(this.repository.save(this.costumer)).thenReturn(this.costumer);
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.reponse);
        Mockito.when(this.mapper.map(this.request, Costumer.class)).thenReturn(this.costumer);

        final CostumerResponse actual = this.costumerService.saveCostumer(this.request);
        this.basicAssertions(actual);
    }

    @Test
    void shouldUpdateCostumerWhenCallingUpdate() {
        final String expected = "Update Working";
        this.request.setName(expected);
        this.costumer.setName(expected);
        this.reponse.setName(expected);

        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.costumer));
        Mockito.when(this.mapper.map(this.costumer, CostumerResponse.class)).thenReturn(this.reponse);

        // Call the method to be tested
        final CostumerResponse actual = this.costumerService.updateCostumer(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(expected, actual.getName());
        Assertions.assertSame(this.reponse, actual);

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(repository).save(this.costumer);
        Mockito.verify(this.mapper).map(this.costumer, CostumerResponse.class);
        Mockito.verifyNoMoreInteractions(this.repository, this.mapper);
    }

    private void basicAssertions(CostumerResponse actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(CostumerResponse.class, actual);
        Assertions.assertSame(this.reponse, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(this.reponse.getEmail(), actual.getEmail());
        Assertions.assertEquals(this.reponse.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(this.reponse.getAddress(), actual.getAddress());
        Assertions.assertEquals(this.reponse.getPhone(), actual.getPhone());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(this.reponse, actual);
    }
}