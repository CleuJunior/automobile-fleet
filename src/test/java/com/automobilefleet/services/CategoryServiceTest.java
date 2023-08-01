package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;
    private Category category;
    private CategoryResponse reponse;
    private CategoryRequest request;
    private final static UUID ID = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");

    @BeforeEach
    void setup() {
        this.category = FactoryUtils.createCategory();
        this.reponse = FactoryUtils.createCategoryResponse();
        this.request = FactoryUtils.createCategoryRequest();
    }

    @Test
    void shouldReturnASingleCategoryWhenCallingListCategories() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.category));

        final CategoryResponse actual = this.service.listCategories().stream()
                .findFirst()
                .orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnOneCategoryById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.ofNullable(this.category));

        final CategoryResponse actual =  this.service.getCategoryById(ID);

        // Assertions
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));
        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(CategoryResponse.class, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldThrowsCategoryNotFoundExceptionWhenIdNonExisting() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getCategoryById(ID));

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldSaveCategoryWhenCallingSaveCategory() {
        Mockito.when(this.repository.save(any(Category.class))).thenReturn(this.category);
        final CategoryResponse actual = this.service.saveCategory(this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(CategoryResponse.class, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());
    }

    @Test
    void shouldUpdateCategoryWhenCallingUpdate() {
        String nameUpdate = "Update Working";
        this.request.setName(nameUpdate);
        this.reponse.setName(nameUpdate);

        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.category));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Category.class))).thenReturn(this.category);


        // Call the method to be tested
        final CategoryResponse actual = this.service.updateCategory(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getDescription(), actual.getDescription());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).save(this.category);
    }
}