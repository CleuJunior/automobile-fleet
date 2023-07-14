package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;

public class BrandNotFoundException extends NotFoundException {

    public BrandNotFoundException() {
        super(ExceptionsConstants.BRAND_NOT_FOUND);
    }
}
