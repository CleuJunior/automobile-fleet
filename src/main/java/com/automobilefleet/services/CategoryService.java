package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
import com.automobilefleet.util.mapper.CategoryMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryResponse> listCategories() {
        return this.repository.findAll().stream()
                .map(CategoryMapperUtils::toCategorResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(UUID id) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        return CategoryMapperUtils.toCategorResponse(response);
    }

    public CategoryResponse saveCategory(CategoryRequest request) {
        Category categorySave = CategoryMapperUtils.toCategory(request);
        return CategoryMapperUtils.toCategorResponse(this.repository.save(categorySave));
    }

    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        response.setName(request.getName());
        response.setDescription(response.getDescription());
        return CategoryMapperUtils.toCategorResponse(this.repository.save(response));
    }
}