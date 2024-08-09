package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CategoryMapper;
import com.automobilefleet.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryResponse> listCategories() {
        var categories = repository.findAll();

        if (categories.isEmpty()) {
            log.info("Empty list of categories");
            return emptyList();
        }

        log.info("Return list of categories");
        return mapper.toCategoryResponseList(categories);
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        var category = findCategoryOrThrow(id);

        log.info("Category id {} found successfully", id);
        return mapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest request) {
        var category = Category.builder()
                .name(request.name())
                .description(request.description())
                .build();

        log.info("Category saved successfully");
        return mapper.toCategoryResponse(repository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        var category = findCategoryOrThrow(id);

        category.setName(request.name());
        category.setDescription(request.description());

        log.info("Category updated successfully");
        return mapper.toCategoryResponse(repository.save(category));
    }

    private Category findCategoryOrThrow(UUID id) {
        var category = repository.findById(id);

        if (category.isEmpty()) {
            log.error("Category id: {} not found", id);
            throw new NotFoundException("category.not.found", id);
        }

        return category.get();
    }

}