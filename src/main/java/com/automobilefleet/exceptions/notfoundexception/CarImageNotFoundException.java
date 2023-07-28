package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;

public class CarImageNotFoundException extends NotFoundException {

    public CarImageNotFoundException() {
        super(ExceptionsConstants.IMAGE_NOT_FOUND);
    }
}
