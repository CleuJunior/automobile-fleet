package com.automobilefleet.exceptions;

public class CategoryNotFoundException extends NotFoundException{

    public CategoryNotFoundException() {
        super("Category not found!");
    }
}
