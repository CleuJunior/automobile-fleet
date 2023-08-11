package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class BrandServiceTest extends ServiceInitialSetup {
    @Mock
    private BrandRepository repository;
    private BrandService service;
    private Brand brand;
    private BrandResponse response;
    private static final UUID ID = UUID.fromString("0a7d6250-0be5-4036-8f23-33dc1762bed0");
    private static final String NAME = "BMW";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2018, 7, 30, 12, 33, 33);

    @BeforeEach
    void setupAttributes() {
        this.service = new BrandService(this.repository);
        this.brand = Brand.builder().id(ID).name(NAME).createdAt(CREATED_AT).build();
        this.response = new BrandResponse(ID, NAME);
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.brand));

        final Optional<BrandResponse> actual = this.service.listBrand().stream().findFirst();

        // Assertions
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(this.response, actual.get());

        // Verifications
        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));

        final BrandResponse actual = this.service.getBrandById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);

    }

    @Override @Test
    void shouldSave() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(Brand.class))).thenReturn(this.brand);

        final BrandRequest request = new BrandRequest(NAME);
        final BrandResponse actual = this.service.saveBrand(request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response, actual);

        // Verifications
        Mockito.verify(this.repository).save(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(this.repository);

    }

    @Override @Test
    void shouldUpdate() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Brand.class))).thenReturn(this.brand);

        final BrandRequest request = new BrandRequest(NAME);
        final BrandResponse brandResponse = this.service.updateBrand(ID, request);

        // Assertions
        Assertions.assertNotNull(brandResponse);
        Assertions.assertEquals(request.name(), brandResponse.name());

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).save(ArgumentMatchers.any(Brand.class));

    }

    @Test
    void shouldDelete() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));

        this.service.deleteBrandById(ID);

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).delete(this.brand);
    }

    @Override @Test
    void shouldThrowErros() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        BrandRequest anyRequest = new BrandRequest("Any");

        Assertions.assertThrows(NotFoundException.class, () -> this.service.getBrandById(ID));
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateBrand(ID, anyRequest));
        Assertions.assertThrows(NotFoundException.class, () -> this.service.deleteBrandById(ID));

        Mockito.verify(this.repository, Mockito.times(3)).findById(ID);
    }
}