package com.automobilefleet.util;

import com.automobilefleet.api.request.CategoryRequest;
import com.automobilefleet.api.response.CategoryResponse;
import com.automobilefleet.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CategoryMapperUtils {

    public static Category toCategory(CategoryRequest req) {
        return new Category(null, req.getName(), req.getDescription(), req.getCreatedAt());
    }

    public static CategoryResponse toCategorResponse(Category cat) {
        return new CategoryResponse(cat.getId(), cat.getName(), cat.getDescription(), cat.getCreatedAt());
    }
}
