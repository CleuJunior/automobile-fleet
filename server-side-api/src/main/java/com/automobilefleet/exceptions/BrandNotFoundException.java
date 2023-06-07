package com.automobilefleet.exceptions;

public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException() {
        super(NotFoundExceptionsConstants.BRAND_NOT_FOUND);
    }
}
