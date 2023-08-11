package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryResponse> listCategories() {
        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(CategoryResponse::new)
                .toList();
    }

    public CategoryResponse getCategoryById(UUID id) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        return new CategoryResponse(response);
    }

    public CategoryResponse saveCategory(CategoryRequest request) {
        Category categorySave = Category.builder()
                .name(request.name())
                .description(request.description())
                .build();

        return new CategoryResponse(this.repository.save(categorySave));
    }

    public CategoryResponse updateCategory(UUID id, CategoryRequest request) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        response.setName(request.name());
        response.setDescription(request.description());
        return new CategoryResponse(this.repository.save(response));
    }
}