package com.automobilefleet.services;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;
    private Category category;
    private CategoryResponse response;
    private CategoryRequest request;
    private final static UUID ID = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    @BeforeEach
    void setupAttributes() {
        this.category = Fixture.from(Category.class).gimme("category");
        this.response = Fixture.from(CategoryResponse.class).gimme("response");
        this.request = Fixture.from(CategoryRequest.class).gimme("request");
    }

    @Test
    void shouldReturnASingleCategoryWhenCallingListCategories() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.category));

        final CategoryResponse actual = this.service.listCategories().stream()
                .findFirst()
                .orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

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
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());

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
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.response.getCreatedAt(), actual.getCreatedAt());
    }

    @Test
    void shouldUpdateCategoryWhenCallingUpdate() {
        String nameUpdate = "Update Working";
        this.request.setName(nameUpdate);
        this.response.setName(nameUpdate);

        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.category));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Category.class))).thenReturn(this.category);


        // Call the method to be tested
        final CategoryResponse actual = this.service.updateCategory(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.getId(), actual.getId());
        Assertions.assertEquals(this.response.getName(), actual.getName());
        Assertions.assertEquals(this.response.getDescription(), actual.getDescription());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).save(this.category);
    }

    @Test
    void shouldThrowsCategoryNotFoundExceptionWhenIdNonExisting() {
        CategoryRequest emptyRequest = new CategoryRequest();
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getCategoryById(ID));
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCategory(ID, emptyRequest));

        Mockito.verify(this.repository, Mockito.times(2)).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }
}