package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.api.dto.request.CostumerRequest;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.costumer = Fixture.from(Costumer.class).gimme("costumer");
        this.reponse = Fixture.from(CostumerResponse.class).gimme("response");
        this.request = Fixture.from(CostumerRequest.class).gimme("request");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAListWithThreeElement() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.costumer));

        final CostumerResponse actual = this.costumerService.listCostumer().stream().findFirst().orElse(null);
        this.basicAssertions(actual);

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
        this.basicAssertions(actual);

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldSaveCostumerWhenCallingSaveCostumer() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(Costumer.class))).thenReturn(this.costumer);
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
        Mockito.when(this.repository.save(ArgumentMatchers.any(Costumer.class))).thenReturn(this.costumer);

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

    @Test
    void shouldThrowsCostumerNotFoundExceptionWhenIdNonExisting() {
        CostumerRequest emptyRequest = new CostumerRequest();
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.costumerService.getCostumerById(ID));
        Assertions.assertThrows(NotFoundException.class, () -> this.costumerService.updateCostumer(ID, emptyRequest));

        Mockito.verify(this.repository, Mockito.times(2)).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
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
    }
}