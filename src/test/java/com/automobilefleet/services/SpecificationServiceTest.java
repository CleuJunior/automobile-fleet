package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.SpecificationMapper;
import com.automobilefleet.repositories.SpecificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class SpecificationServiceTest extends ServiceInitialSetup{
    private SpecificationService service;
    @Mock
    private SpecificationRepository repository;
    private final SpecificationMapper mapper = new SpecificationMapper();
    private Specification specification;
    private SpecificationRequest request;
    private SpecificationResponse response;
    private static final UUID ID = UUID.randomUUID();

    @BeforeEach
    void setupAttributes() {
        this.service = new SpecificationService(this.repository, this.mapper);

        this.specification = Fixture.from(Specification.class).gimme("specification");
        this.response = new SpecificationResponse(this.specification.getId(), this.specification.getName(),
                this.specification.getDescription());

        this.request = new SpecificationRequest(this.specification.getName(), this.specification.getDescription());
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.specification));

        final SpecificationResponse actual = this.service.listSpecifications()
                .stream()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.response.name(), actual.name());
        Assertions.assertEquals(this.response.description(), actual.description());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.ofNullable(this.specification));

        final SpecificationResponse actual = this.service.getSpecification(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND));
        Assertions.assertInstanceOf(SpecificationResponse.class, actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.response.name(), actual.name());
        Assertions.assertEquals(this.response.description(), actual.description());

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }


    @Override @Test
    void shouldSave() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(Specification.class))).thenReturn(this.specification);
        final SpecificationResponse actual = this.service.saveSpecification(this.request);

        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(SpecificationResponse.class, actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.request.name(), actual.name());
        Assertions.assertEquals(this.request.description(), actual.description());
    }

    @Override @Test
    void shouldUpdate() {
        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.specification));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Specification.class))).thenReturn(this.specification);

        // Call the method to be tested
        final SpecificationResponse actual = this.service.updateSpecification(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.request.name(), actual.name());
        Assertions.assertEquals(this.request.description(), actual.description());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(repository).save(this.specification);
    }

    @Override @Test
    void shouldThrowErros() {
        // Test for find
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getSpecification(ID));

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);

        // Test for Update
        UUID nonExistingId = UUID.randomUUID();
        Mockito.when(this.repository.findById(nonExistingId)).thenReturn(Optional.empty());

        SpecificationRequest emptyRequest = new SpecificationRequest("", "");

        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.updateSpecification(nonExistingId, emptyRequest));

        Mockito.verify(this.repository).findById(nonExistingId);
        Mockito.verifyNoMoreInteractions(this.repository);
    }
}