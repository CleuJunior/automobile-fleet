package com.automobilefleet.services;

import com.automobilefleet.api.mapper.CategoryMapper;
import com.automobilefleet.api.reponse.CategoryResponse;
import com.automobilefleet.api.request.CategoryRequest;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.CategoryNotFoundException;
import com.automobilefleet.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryResponse> listCategories() {
        List<Category> category = repository.findAll();

        return CategoryMapper.toCategoryResponseList(category);

    }

    public CategoryResponse getCategory(Long id) {
        Category response = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        return CategoryMapper.toCategoryResponse(response);
    }

    public CategoryResponse saveCategory(CategoryRequest request) {
        Category categorySave = CategoryMapper.toCategory(request);
        repository.save(categorySave);

        return CategoryMapper.toCategoryResponse(categorySave);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category response = repository.findById(id).get();
        CategoryMapper.updateCategory(response, request);

        repository.save(response);

        return CategoryMapper.toCategoryResponse(response);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

}
