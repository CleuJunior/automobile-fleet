package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final ModelMapper mapper;

    public List<CategoryResponse> listCategories() {
        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(category -> this.mapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(UUID id) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        return this.mapper.map(response, CategoryResponse.class);
    }

    public CategoryResponse saveCategory(CategoryRequest request) {
        Category categorySave = this.mapper.map(request, Category.class);
        return this.mapper.map(this.repository.save(categorySave), CategoryResponse.class);
    }

    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        response.setName(request.getName());
        response.setDescription(request.getDescription());
        return this.mapper.map(this.repository.save(response), CategoryResponse.class);
    }
}