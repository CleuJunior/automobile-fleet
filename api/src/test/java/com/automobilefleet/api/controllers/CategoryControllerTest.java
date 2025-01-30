package com.automobilefleet.api.controllers;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService service;
    @Mock
    private CategoryResponse response;
    @Mock
    private CategoryRequest request;
    @InjectMocks
    private CategoryController controller;

    private static final UUID ID = randomUUID();

    @Test
    void shouldGetCategoryByIdAndStatusCodeOK() {
        given(service.getCategoryById(ID)).willReturn(response);

        var result = controller.getCategoryById(ID);

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(response);

        verify(service).getCategoryById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetListCategoryAndStatusCodeOK() {
        given(service.listCategories()).willReturn(singletonList(response));

        var result = controller.listOfCategory();

        then(result.getStatusCode()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(singletonList(response));

        verify(service).listCategories();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shoulSaveCategoryAndStatusCodeCreated() {
        given(service.saveCategory(request)).willReturn(response);

        var result = controller.saveCategory(request);

        then(result.getStatusCode()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(response);

        verify(service).saveCategory(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateCategoryAndStatusCodeAccepted() {
        given(service.updateCategory(ID, request)).willReturn(response);

        var result = controller.updateCategory(ID, request);

        then(result.getStatusCode()).isEqualTo(ACCEPTED);
        then(result.getBody()).isEqualTo(response);

        verify(service).updateCategory(ID, request);
        verifyNoMoreInteractions(service);
    }

}