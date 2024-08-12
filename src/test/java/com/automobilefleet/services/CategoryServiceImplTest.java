package com.automobilefleet.services;


import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CategoryMapper;
import com.automobilefleet.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository repository;
    @Mock
    private CategoryMapper mapper;
    @Mock
    private Category category;
    @Mock
    private CategoryResponse response;
    @Mock
    private CategoryRequest request;
    @InjectMocks
    private CategoryServiceImpl service;

    private static final UUID ID = randomUUID();
    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 1);

    @Test
    void shouldReturnSingleListCategory() {
        given(repository.findAll()).willReturn(singletonList(category));
        given(mapper.toCategoryResponseList(singletonList(category))).willReturn(singletonList(response));

        var actual = service.listCategories();

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(repository).findAll();
        verify(mapper).toCategoryResponseList(singletonList(category));
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }


    @Test
    void shouldReturnEmptyListCategory() {
        given(repository.findAll()).willReturn(emptyList());

        var actual = service.listCategories();

        then(actual).isEmpty();

        verify(repository).findAll();
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnCategoryById() {
        given(repository.findById(ID)).willReturn(Optional.of(category));
        given(mapper.toCategoryResponse(category)).willReturn(response);

        var actual = service.getCategoryById(ID);

        // Assertions
        then(actual).isEqualTo(response);

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldThrowErrorWhenCategoryIdNotFound() {
        given(repository.findById(ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getCategoryById(ID));

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }


    @Test
    void shouldSaveCategory() {
        given(repository.save(any(Category.class))).willReturn(category);
        given(mapper.toCategoryResponse(category)).willReturn(response);

        var actual = service.saveCategory(request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(repository).save(any(Category.class));
        verify(mapper).toCategoryResponse(category);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldUpdateCategory() {
        given(repository.findById(ID)).willReturn(Optional.of(category));
        given(repository.save(category)).willReturn(category);
        given(mapper.apply(category, request)).willReturn(category);
        given(mapper.toCategoryResponse(category)).willReturn(response);

        var actual = service.updateCategory(ID, request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(repository).findById(ID);
        verify(repository).save(category);
        verify(mapper).apply(category, request);
        verify(mapper).toCategoryResponse(category);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

}