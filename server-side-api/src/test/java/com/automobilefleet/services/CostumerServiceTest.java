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

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CostumerServiceTest  {
    @InjectMocks
    private CostumerService costumerService;
    @Mock
    private CostumerRepository repository;
    private Costumer costumer;
    private CostumerRequest request;
    private CostumerResponse reponse;
    private static final UUID ID = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");

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

        final CostumerResponse actual = this.costumerService.listCostumer().stream().findFirst().orElse(null);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getBirthdate(), actual.getBirthdate());
        Assertions.assertEquals(this.reponse.getEmail(), actual.getEmail());
        Assertions.assertEquals(this.reponse.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(this.reponse.getAddress(), actual.getAddress());
        Assertions.assertEquals(this.reponse.getPhone(), actual.getPhone());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnOneCostumerById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.ofNullable(this.costumer));

        final CostumerResponse actual = this.costumerService.getCostumerById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND));
        Assertions.assertInstanceOf(CostumerResponse.class, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getBirthdate(), actual.getBirthdate());
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
        Costumer costumerSaveTest = Costumer.builder()
                .name(this.costumer.getName())
                .birthdate(this.costumer.getBirthdate())
                .email(this.costumer.getEmail())
                .driverLicense(this.costumer.getDriverLicense())
                .address(this.costumer.getAddress())
                .phone(this.costumer.getPhone())
                .build();

        Mockito.when(this.repository.save(costumerSaveTest)).thenReturn(this.costumer);
        final CostumerResponse actual = this.costumerService.saveCostumer(this.request);
        this.basicAssertions(actual);
    }

    private void basicAssertions(CostumerResponse actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(CostumerResponse.class, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getBirthdate(), actual.getBirthdate());
        Assertions.assertEquals(this.reponse.getEmail(), actual.getEmail());
        Assertions.assertEquals(this.reponse.getDriverLicense(), actual.getDriverLicense());
        Assertions.assertEquals(this.reponse.getAddress(), actual.getAddress());
        Assertions.assertEquals(this.reponse.getPhone(), actual.getPhone());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());
        Assertions.assertEquals(this.reponse.getUpdatedAt(), actual.getUpdatedAt());
    }

    @Test
    void shouldUpdateCostumerWhenCallingUpdate() {
        final String expected = "Update Working";
        this.request.setName(expected);
        this.costumer.setName(expected);
        this.reponse.setName(expected);

        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.costumer));

        // Call the method to be tested
        final CostumerResponse actual = this.costumerService.updateCostumer(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(expected, actual.getName());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(repository).save(this.costumer);
    }
}