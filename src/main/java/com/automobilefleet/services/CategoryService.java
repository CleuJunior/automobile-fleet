package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;


public interface CategoryService {


    List<CategoryResponse> listCategories();

    CategoryResponse getCategoryById(UUID id);

    CategoryResponse saveCategory(CategoryRequest request);

    CategoryResponse updateCategory(UUID id, CategoryRequest request);

}