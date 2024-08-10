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

import java.util.Collections;
import java.util.List;
import java.util.UUID;


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
            return Collections.emptyList();
        }

        log.info("Return list of categories");
        return mapper.toCategoryResponseList(categories);
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        log.info("Searching for category id: {}", id);
        return repository.findById(id)
                .map(mapper::toCategoryResponse)
                .orElseThrow(() -> new NotFoundException("category.not.found", id));
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest request) {
        log.info("Category saved successfully");
        return mapper.toCategoryResponse(repository.save(new Category(request)));
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        log.info("Searching for category id: {}", id);
        return repository.findById(id)
                .map(current -> mapper.apply(current,request))
                .map(repository::save)
                .map(mapper::toCategoryResponse)
                .orElseThrow(() -> new NotFoundException("category.not.found", id));
    }
}