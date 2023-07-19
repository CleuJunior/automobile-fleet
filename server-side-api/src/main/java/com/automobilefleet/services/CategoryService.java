package com.automobilefleet.services;

import com.automobilefleet.api.response.CategoryResponse;
import com.automobilefleet.api.request.CategoryRequest;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    public List<CategoryResponse> listCategories() {
        return this.repository.findAll().stream()
                .map(category -> this.mapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        return this.mapper.map(response, CategoryResponse.class);
    }

    public CategoryResponse saveCategory(CategoryRequest request) {
        Category categorySave = this.mapper.map(request, Category.class);
        categorySave = this.repository.save(categorySave);

        return this.mapper.map(categorySave, CategoryResponse.class);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category response = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionsConstants.CATEGORY_NOT_FOUND));

        response.setName(request.getName());
        response.setDescription(response.getDescription());
        this.repository.save(response);

        return this.mapper.map(response, CategoryResponse.class);
    }

}
