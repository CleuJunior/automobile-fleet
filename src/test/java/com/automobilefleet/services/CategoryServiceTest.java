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
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest extends ServiceInitialSetup{
    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;
    @Mock
    private ModelMapper mapper;
    private Category category;
    private CategoryResponse response;
    private CategoryRequest request;
    private final static UUID ID = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");

    @BeforeEach
    void setupAttributes() {
        this.category = Fixture.from(Category.class).gimme("category");
        this.response = new CategoryResponse(this.category);
        this.request = new CategoryRequest(this.category.getName(), this.category.getDescription());
    }

    @Override @Test
    void shouldReturnSingleList() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.category));
        Mockito.when(this.mapper.map(this.category, CategoryResponse.class)).thenReturn(this.response);

        final CategoryResponse actual = this.service.listCategories().stream()
                .findFirst()
                .orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.response.name(), actual.name());
        Assertions.assertEquals(this.response.description(), actual.description());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Override @Test
    void shouldReturnById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.category));
        Mockito.when(this.mapper.map(this.category, CategoryResponse.class)).thenReturn(this.response);

        final CategoryResponse actual =  this.service.getCategoryById(ID);

        // Assertions
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));
        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(CategoryResponse.class, actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.response.name(), actual.name());
        Assertions.assertEquals(this.response.description(), actual.description());

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }


    @Override @Test
    void shouldSave() {
        Mockito.when(this.repository.save(any(Category.class))).thenReturn(this.category);
        Mockito.when(this.mapper.map(this.category, CategoryResponse.class)).thenReturn(this.response);
        Mockito.when(this.mapper.map(this.request, Category.class)).thenReturn(this.category);

        final CategoryResponse actual = this.service.saveCategory(this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertInstanceOf(CategoryResponse.class, actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.response.name(), actual.name());
        Assertions.assertEquals(this.response.description(), actual.description());
    }

    @Override @Test
    void shouldUpdate() {
        // Config mocks behavior
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.of(this.category));
        Mockito.when(this.repository.save(ArgumentMatchers.any(Category.class))).thenReturn(this.category);
        Mockito.when(this.mapper.map(this.category, CategoryResponse.class)).thenReturn(this.response);

        // Call the method to be tested
        final CategoryResponse actual = this.service.updateCategory(ID, this.request);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(this.response.id(), actual.id());
        Assertions.assertEquals(this.response.name(), actual.name());
        Assertions.assertEquals(this.response.description(), actual.description());

        // Check mock interactions
        Mockito.verify(this.repository).findById(ID);
        Mockito.verify(this.repository).save(this.category);
    }

    @Override @Test
    void shouldThrowErros() {
        CategoryRequest emptyRequest = new CategoryRequest("", "");
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> this.service.getCategoryById(ID));
        Assertions.assertThrows(NotFoundException.class, () -> this.service.updateCategory(ID, emptyRequest));

        Mockito.verify(this.repository, Mockito.times(2)).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }
}