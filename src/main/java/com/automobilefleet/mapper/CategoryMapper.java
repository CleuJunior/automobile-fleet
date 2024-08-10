package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
public class CategoryMapper {

    public Category toCategory(CategoryResponse categoryResponse) {
        return Category.builder()
                .id(categoryResponse.id())
                .name(categoryResponse.name())
                .description(categoryResponse.description())
                .createdAt(categoryResponse.createdAt())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt()
        );
    }

    public List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        return categories.stream()
                .filter(Objects::nonNull)
                .map(this::toCategoryResponse)
                .toList();
    }

    public Page<CategoryResponse> toCategoryResponsePage(Page<Category> categories, int page, int size) {
        var total = categories.getTotalElements();
        var response = toCategoryResponseList(categories.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }

    public Category apply(Category current, CategoryRequest request) {
        current.setName(request.name());
        current.setDescription(request.description());

        return current;
    }
}
