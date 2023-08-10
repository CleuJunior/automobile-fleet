package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.SpecificationRepository;
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
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class SpecificationServiceTest {
    @InjectMocks
    private SpecificationService service;
    @Mock
    private SpecificationRepository repository;
    @Mock
    private ModelMapper mapper;
    private Specification specification;
    private SpecificationRequest request;
    private SpecificationResponse response;
    private static final UUID ID = UUID.fromString("6b83e4cd-ead6-4af0-8e1e-4c332a842717");

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.specification = Fixture.from(Specification.class).gimme("specification");
        this.response = Fixture.from(SpecificationResponse.class).gimme("response");
        this.request = Fixture.from(SpecificationRequest.class).gimme("request");
    }

    @Test
    void shouldReturnSingleListSpecification() {
        Mockito.when(this.mapper.map(this.specification, SpecificationResponse.class)).thenReturn(this.response);
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

    @Test
    void shouldReturnOneSpecificationById() {
        Mockito.when(this.mapper.map(this.specification, SpecificationResponse.class)).thenReturn(this.response);
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


    @Test
    void shouldSaveSpecificationWhenCallingSaveSpecification() {
        Mockito.when(this.mapper.map(this.specification, SpecificationResponse.class)).thenReturn(this.response);
        Mockito.when(this.repository.save(ArgumentMatchers.any(Specification.class))).thenReturn(this.specification);
        final SpecificationResponse actual = this.service.saveSpecification(this.request);

        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(SpecificationResponse.class, actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.request.getName(), actual.name());
        Assertions.assertEquals(this.request.getDescription(), actual.description());
    }

    @Test
    void shouldUpdateCostumerWhenCallingUpdate() {
        // Config mocks behavior
        Mockito.when(this.mapper.map(this.specification, SpecificationResponse.class)).thenReturn(this.response);
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.specification));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Specification.class))).thenReturn(this.specification);

        // Call the method to be tested
        final SpecificationResponse actual = this.service.updateSpecification(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.request.getName(), actual.name());
        Assertions.assertEquals(this.request.getDescription(), actual.description());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(repository).save(this.specification);
    }

    @Test
    void shouldThrowsSpecificationNotFoundExceptionWhenIdNonExisting() {
        // Test for find
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getSpecification(ID));

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);

        // Test for Update
        UUID nonExistingId = UUID.randomUUID();
        Mockito.when(this.repository.findById(nonExistingId)).thenReturn(Optional.empty());

        SpecificationRequest emptyRequest = new SpecificationRequest();

        Assertions.assertThrows(NotFoundException.class,
                () -> this.service.updateSpecification(nonExistingId, emptyRequest));

        Mockito.verify(this.repository).findById(nonExistingId);
        Mockito.verifyNoMoreInteractions(this.repository);
    }
}