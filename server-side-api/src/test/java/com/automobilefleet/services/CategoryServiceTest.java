package com.automobilefleet.services;

import com.automobilefleet.api.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
import com.automobilefleet.utils.FactoryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;
    @Mock
    private ModelMapper mapper;
    private Category category;
    private CategoryResponse reponse;
    private final static Long ID = 1L;
    @BeforeEach
    void setup() {
        this.category = FactoryUtils.createCategory();
        this.reponse = FactoryUtils.createCategoryResponse();
    }

    @Test
    void shouldReturnASingleCategoryWhenCallingListCategories() {
        Mockito.when(this.repository.findAll()).thenReturn(Collections.singletonList(this.category));
        Mockito.when(this.mapper.map(this.category, CategoryResponse.class)).thenReturn(this.reponse);

        final CategoryResponse actual = this.service.listCategories()
                .stream()
                .findFirst()
                .orElse(null);

        // Assertions
        Assertions.assertNotNull(actual);
        Assertions.assertSame(this.reponse, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).findAll();
        Mockito.verifyNoMoreInteractions(this.repository);
    }

    @Test
    void shouldReturnOneCostumerById() {
        Mockito.when(this.repository.findById(ID)).thenReturn(Optional.ofNullable(this.category));
        Mockito.when(this.mapper.map(this.category, CategoryResponse.class)).thenReturn(this.reponse);

        final CategoryResponse actual =  this.service.getCategoryById(ID);

        // Assertions
        Assertions.assertDoesNotThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        Assertions.assertNotNull(actual);
        Assertions.assertSame(this.reponse, actual);
        Assertions.assertInstanceOf(CategoryResponse.class, actual);

        Assertions.assertSame(this.reponse, actual);
        Assertions.assertEquals(this.reponse.getId(), actual.getId());
        Assertions.assertEquals(this.reponse.getName(), actual.getName());
        Assertions.assertEquals(this.reponse.getDescription(), actual.getDescription());
        Assertions.assertEquals(this.reponse.getCreatedAt(), actual.getCreatedAt());

        Mockito.verify(this.repository).findById(ID);
        Mockito.verifyNoMoreInteractions(this.repository);
    }


}