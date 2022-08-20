package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.CategoryResponse;
import com.automobilefleet.api.request.CategoryRequest;
import com.automobilefleet.entities.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryMapper {

    private CategoryMapper() { }

    public static Category toCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setCreatedAt(category.getCreatedAt());

        return response;
    }

    public static List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        List<CategoryResponse> response = new ArrayList<>();
        categories.forEach(category -> response.add(toCategoryResponse(category)));

        return response;
    }

    public static void updateCategory(Category category, CategoryRequest request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
    }
}
