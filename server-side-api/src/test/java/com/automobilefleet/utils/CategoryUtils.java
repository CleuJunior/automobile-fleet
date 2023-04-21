package com.automobilefleet.utils;

import com.automobilefleet.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryUtils {
    private static final Long EXISTING_ID = 1L;
    private static final Long NON_EXISTING_ID = 2147483647L;
    private static final String NAME = "Name category";
    private static final String NAME_FOR_LIST = "Name category number %d";
    private static final String DESCRIPTION = "Description Category";
    private static final String DESCRIPTION_FOR_LIST = "Description category number %d";


    public static Category ofCategory() {
        return new Category(NAME, DESCRIPTION);
    }

    private static Category ofCategory(String name, String description) {
        return new Category(NAME, DESCRIPTION);
    }

    public static List<Category> ofListCategory(int quantity) {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            categories.add(new Category(String.format(NAME_FOR_LIST, i), String.format(DESCRIPTION, i)));
        }

        return categories;
    }
}
