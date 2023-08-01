package com.automobilefleet.util.mapper;

import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CategoryMapperUtils {

    public static Category toCategory(CategoryRequest req) {
        return Category.builder()
                .name(req.getName())
                .description(req.getDescription())
                .build();
    }

    public static CategoryResponse toCategorResponse(Category cat) {
        return new CategoryResponse(cat.getId(), cat.getName(), cat.getDescription(), cat.getCreatedAt());
    }
}
