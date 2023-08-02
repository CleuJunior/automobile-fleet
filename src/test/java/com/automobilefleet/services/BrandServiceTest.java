package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class BrandServiceTest {
    @InjectMocks
    private BrandService service;
    @Mock
    private BrandRepository repository;
    private Brand brand;
    private BrandResponse response;
    private BrandRequest request;
    private static final UUID ID = UUID.fromString("0a7d6250-0be5-4036-8f23-33dc1762bed0");

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.brand = Fixture.from(Brand.class).gimme("brand");
        this.response = Fixture.from(BrandResponse.class).gimme("response");
        this.request = Fixture.from(BrandRequest.class).gimme("request");
    }

    @Test
    void shouldReturnListOfBrandsWhenCallingFindAll() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.brand));

        final BrandResponse actual = this.service.listBrand().stream().findFirst().orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

        // Verifications
        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnABrandWhenCallingGetBrandById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));

        final BrandResponse actual = this.service.getBrandById(ID);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnBrandWhenCallingSaveBrand() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(Brand.class))).thenReturn(this.brand);

        final BrandResponse actual = this.service.saveBrand(new BrandRequest("BMW"));

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

        // Verifications
        Mockito.verify(this.repository).save(ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldUpdateExistingBrandWhenCallingUpdateBrand() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Brand.class))).thenReturn(this.brand);

        final String expected = "Test Update";
        final BrandResponse brandResponse = this.service.updateBrand(ID, new BrandRequest(expected));

        // Assertions
        Assertions.assertNotNull(brandResponse);
        Assertions.assertEquals(expected, brandResponse.getName());

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).save(ArgumentMatchers.any(Brand.class));
    }

    @Test
    void shouldDeleteBrandWhenCallinDeleteBrand() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));

        this.service.deleteBrandById(ID);

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).delete(this.brand);
    }

    @Test
    void shouldThrowBrandNotFoundExceptionWhenCallingGetBrand() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        BrandRequest emptyRequest = new BrandRequest();

        NotFoundException exceptionFindById =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.getBrandById(ID));

        NotFoundException exceptionUpdate =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.updateBrand(ID, emptyRequest));

        NotFoundException exceptionDelete =
                Assertions.assertThrows(NotFoundException.class, () -> this.service.deleteBrandById(ID));

        Assertions.assertEquals(ExceptionsConstants.BRAND_NOT_FOUND, exceptionFindById.getMessage());
        Assertions.assertEquals(ExceptionsConstants.BRAND_NOT_FOUND, exceptionUpdate.getMessage());
        Assertions.assertEquals(ExceptionsConstants.BRAND_NOT_FOUND, exceptionDelete.getMessage());

        Mockito.verify(this.repository, Mockito.times(3)).findById(ID);
    }
}