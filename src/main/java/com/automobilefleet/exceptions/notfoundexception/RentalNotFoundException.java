package com.automobilefleet.exceptions.notfoundexception;

import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;

public class RentalNotFoundException extends NotFoundException {

    public RentalNotFoundException() {
        super(ExceptionsConstants.RENTAL_NOT_FOUND);
    }
}
