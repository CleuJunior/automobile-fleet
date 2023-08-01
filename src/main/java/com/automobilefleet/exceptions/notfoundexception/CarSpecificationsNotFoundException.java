package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;

public class CarSpecificationsNotFoundException extends NotFoundException {

    public CarSpecificationsNotFoundException() {
        super(ExceptionsConstants.CAR_SPECIFICATION_NOT_FOUND);
    }
}
