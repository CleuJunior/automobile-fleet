package com.automobilefleet.exceptions;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super(NotFoundExceptionsConstants.CATEGORY_NOT_FOUND);
    }
}
