package com.automobilefleet.exceptions;

public class CostumerNotFoundException extends NotFoundException{

    public CostumerNotFoundException() {
        super("Costumer not found!");
    }
}
