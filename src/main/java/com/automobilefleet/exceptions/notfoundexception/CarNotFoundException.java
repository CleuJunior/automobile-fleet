package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;

public class CarNotFoundException extends NotFoundException {

    public CarNotFoundException() {
        super(ExceptionsConstants.CAR_NOT_FOUND);
    }
}
