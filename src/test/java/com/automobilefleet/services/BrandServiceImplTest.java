package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.BrandMapper;
import com.automobilefleet.repositories.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@ExtendWith(SpringExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository repository;
    @Mock
    private Brand brand;
    @Mock
    private BrandResponse response;
    @Mock
    private BrandRequest request;
    @Mock
    private BrandMapper mapper;

    @InjectMocks
    private BrandServiceImpl service;

    private static final UUID ID = randomUUID();
    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 1);

    @Test
    void shouldReturnSingleList() {
        given(repository.findAll()).willReturn(singletonList(brand));
        given(mapper.toListBrandResponse(singletonList(brand))).willReturn(singletonList(response));

        var actual = service.listBrand();

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll();
        verify(mapper).toListBrandResponse(singletonList(brand));
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListBrand() {
        given(repository.findAll()).willReturn(emptyList());

        var actual = service.listBrand();

        then(actual).isEmpty();

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnPageBrand() {
        var brandPage = new PageImpl<>(singletonList(brand), PAGE_REQUEST, 1);
        var brandResponsePage = new PageImpl<>(singletonList(response), PAGE_REQUEST, 1);

        given(repository.findAll(PAGE_REQUEST)).willReturn(brandPage);
        given(mapper.toBrandResponsePage(brandPage, 0, 1)).willReturn(brandResponsePage);

        var actual = service.pageBrand(0, 1);

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll(PAGE_REQUEST);
        verify(mapper).toBrandResponsePage(brandPage, 0, 1);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyPageBrand() {
        given(repository.findAll(PAGE_REQUEST)).willReturn(Page.empty());

        var actual = service.pageBrand(0, 1);

        then(actual).isEmpty();

        verify(repository).findAll(PAGE_REQUEST);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnBrandById() {
        given(repository.findById(ID)).willReturn(of(brand));
        given(mapper.toBrandResponse(brand)).willReturn(response);

        var actual = service.getBrandById(ID);

        then(actual).isEqualTo(response);

        verify(repository).findById(ID);
        verify(mapper).toBrandResponse(brand);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shoulThrowErrorWhendReturnBrandByIdNonExiting() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getBrandById(ID));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldSaveBrand() {
        given(repository.save(any(Brand.class))).willReturn(brand);
        given(mapper.toBrandResponse(brand)).willReturn(response);

        var actual = service.saveBrand(request);

        // Assertions
        then(actual).isEqualTo(response);

        // Verifications
        verify(repository).save(any(Brand.class));
        verify(mapper).toBrandResponse(brand);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldUpdateBrand() {
        given(repository.findById(ID)).willReturn(of(brand));
        given(repository.save(brand)).willReturn(brand);
        given(mapper.apply(brand, request)).willReturn(brand);
        given(mapper.toBrandResponse(brand)).willReturn(response);

        var actual = service.updateBrand(ID, request);

        then(actual).isEqualTo(response);

        // Verifications
        verify(repository).findById(ID);
        verify(repository).save(brand);
        verify(mapper).apply(brand, request);
        verify(mapper).toBrandResponse(brand);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldDeleteBrand() {
        given(repository.findById(ID)).willReturn(of(brand));
        willDoNothing().given(repository).delete(brand);

        service.deleteBrandById(ID);

        // Verifications
        verify(repository).findById(ID);
        verify(repository).delete(brand);
        verifyNoMoreInteractions(repository);
    }
}