package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.utils.FactoryUtils;
import org.junit.jupiter.api.Assertions;
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

import static com.automobilefleet.constant.ExpectedAttributesConstant.EXPECTED_CREATED_AT_BRAND;
import static com.automobilefleet.constant.ExpectedAttributesConstant.EXPECTED_ID_BRAND;
import static com.automobilefleet.constant.ExpectedAttributesConstant.EXPECTED_NAME_BRAND;

@ExtendWith(SpringExtension.class)
class BrandServiceTest {
    @InjectMocks
    private BrandService service;
    @Mock
    private BrandRepository repository;
    private static final UUID ID = UUID.fromString("0a7d6250-0be5-4036-8f23-33dc1762bed0");
    private Brand brand;

    @BeforeEach
    void setUp() {
        this.brand = FactoryUtils.createBrand();
    }

    @Test
    void shouldReturnListOfBrandsWhenCallingFindAll() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.brand));

        final BrandResponse actual = this.service.listBrand().stream().findFirst().orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_ID_BRAND, actual.getId());
        Assertions.assertEquals(EXPECTED_NAME_BRAND, actual.getName());
        Assertions.assertEquals(EXPECTED_CREATED_AT_BRAND, actual.getCreatedAt());

        // Verifications
        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnABrandWhenCallingGetBrandById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.brand));

        final BrandResponse actual = this.service.getBrandById(EXPECTED_ID_BRAND);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_ID_BRAND, actual.getId());
        Assertions.assertEquals(EXPECTED_NAME_BRAND, actual.getName());
        Assertions.assertEquals(EXPECTED_CREATED_AT_BRAND, actual.getCreatedAt());

        // Verifications
        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldThrowBrandNotFoundExceptionWhenCallingGetBrand() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(
                NotFoundException.class, () -> this.service.getBrandById(ID)
        );

        Assertions.assertThrows(NotFoundException.class, () -> this.service.getBrandById(ID));
        Assertions.assertEquals("Brand not found!", exception.getMessage());
        Mockito.verify(this.repository, Mockito.times(2)).findById(ID);
    }

    @Test
    void shouldReturnBrandWhenCallingSaveBrand() {
        Mockito.when(this.repository.save(ArgumentMatchers.any(Brand.class))).thenReturn(this.brand);

        final BrandResponse actual = this.service.saveBrand(new BrandRequest("BMW"));

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(EXPECTED_ID_BRAND, actual.getId());
        Assertions.assertEquals(EXPECTED_NAME_BRAND, actual.getName());
        Assertions.assertEquals(EXPECTED_CREATED_AT_BRAND, actual.getCreatedAt());

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
}