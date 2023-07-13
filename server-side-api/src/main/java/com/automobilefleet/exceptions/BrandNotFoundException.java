package com.automobilefleet.exceptions;

public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException() {
        super(ExceptionsConstants.BRAND_NOT_FOUND);
    }
}
