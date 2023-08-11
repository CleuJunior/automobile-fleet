package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import com.automobilefleet.api.dto.request.CostumerRequest;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

class CostumerServiceTest extends SpecificationServiceTest {
    private CostumerService service;
    @Mock
    private CostumerRepository repository;
    private Costumer costumer;
    private CostumerRequest request;
    private CostumerResponse response;
    private static final UUID ID = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");

    @BeforeEach
    void setupAttributes() {
        this.service = new CostumerService(this.repository);
        this.costumer = Fixture.from(Costumer.class).gimme("costumer");
        this.response = new CostumerResponse(this.costumer);
        this.request = new CostumerRequest(this.costumer.getName(), this.costumer.getBirthdate(), this.costumer.getEmail(),
                this.costumer.getDriverLicense(), this.costumer.getAddress(), this.costumer.getPhone());
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.costumer));

        final Optional<CostumerResponse> actual = this.service.listCostumer().stream().findFirst();

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.costumer));

        final CostumerResponse actual = this.service.getCostumerById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Override @Test
    void shouldSave() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(Costumer.class))).thenReturn(this.costumer);

        final CostumerResponse actual = this.service.saveCostumer(this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);
    }

    @Override @Test
    void shouldUpdate() {
        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.costumer));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Costumer.class))).thenReturn(this.costumer);

        // Call the method to be tested
        final CostumerResponse actual = this.service.updateCostumer(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(repository).save(this.costumer);
    }

    @Override @Test
    void shouldThrowErros() {
        CostumerRequest emptyRequest = new CostumerRequest(
                "",
                null,
                "",
                "",
                "",
                ""
        );

        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());

        NotFoundException exceptionFindById =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.getCostumerById(ID));

        NotFoundException exceptionUpdate =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCostumer(ID, emptyRequest));

        Assertions.assertEquals(ExceptionsConstants.COSTUMER_NOT_FOUND, exceptionFindById.getMessage());
        Assertions.assertEquals(ExceptionsConstants.COSTUMER_NOT_FOUND, exceptionUpdate.getMessage());

        Mockito.verify(this.repository, Mockito.times(2)).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }
}