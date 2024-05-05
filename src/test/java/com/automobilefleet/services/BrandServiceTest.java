package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.ZoneId.systemDefault;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@ExtendWith(SpringExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository repository;
    @InjectMocks
    private BrandService service;
    private Brand brand;
    private BrandResponse response;
    private static final Faker faker = new Faker();
    private static final UUID ID = randomUUID();
    private static final String NAME = faker.programmingLanguage().name();
    private static final LocalDateTime CREATED_AT = faker.date().birthday().toInstant().atZone(systemDefault()).toLocalDateTime();

    @BeforeEach
    void setUp() {
        brand = Brand.builder()
                .id(ID)
                .name(NAME)
                .createdAt(CREATED_AT)
                .build();

        response = new BrandResponse(ID, NAME, CREATED_AT);
    }

    @Test
    void shouldReturnSingleList() {
        given(repository.findAll()).willReturn(singletonList(brand));

        var actual = service.listBrand();

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnById() {
        given(repository.findById(ID)).willReturn(of(brand));

        var actual = service.getBrandById(ID);

        then(actual).isEqualTo(response);

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldSave() {
        var brandArg = Brand.builder().name(NAME).build();
        var request = new BrandRequest(NAME);

        given(repository.save(brandArg)).willReturn(brand);

        var actual = service.saveBrand(request);

        // Assertions
        then(actual).isEqualTo(response);

        // Verifications
        verify(repository).save(brandArg);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldUpdate() {
        var updateName = faker.programmingLanguage().name();
        var request = new BrandRequest(updateName);
        var brandUpdate = Brand.builder().id(ID).name(updateName).createdAt(CREATED_AT).build();

        given(repository.findById(ID)).willReturn(of(brand));
        given(repository.save(brandUpdate)).willReturn(brandUpdate);

        var actual = service.updateBrand(ID, request);

        then(actual.name()).isEqualTo(updateName);
        then(actual.id()).isEqualTo(brand.getId());

        // Verifications
        verify(repository).findById(ID);
        verify(repository).save(brandUpdate);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldDelete() {
        given(repository.findById(ID)).willReturn(of(brand));
        willDoNothing().given(repository).delete(brand);

        service.deleteBrandById(ID);

        // Verifications
        verify(repository).findById(ID);
        verify(repository).delete(brand);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowErros() {
        given(repository.findById(ID)).willReturn(empty());
        var request = new BrandRequest("Any");

        assertThrows(NotFoundException.class, () -> service.getBrandById(ID));
        assertThrows(NotFoundException.class, () -> service.updateBrand(ID, request));
        assertThrows(NotFoundException.class, () -> service.deleteBrandById(ID));

        verify(repository, times(3)).findById(ID);
    }
}