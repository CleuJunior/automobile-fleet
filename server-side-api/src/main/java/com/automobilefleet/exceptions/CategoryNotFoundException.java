package com.automobilefleet.exceptions;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super(ExceptionsConstants.CATEGORY_NOT_FOUND);
    }
}
