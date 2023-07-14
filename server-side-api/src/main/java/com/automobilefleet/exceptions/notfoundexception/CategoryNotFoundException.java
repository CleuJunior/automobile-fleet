package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super(ExceptionsConstants.CATEGORY_NOT_FOUND);
    }
}
