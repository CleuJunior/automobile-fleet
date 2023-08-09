package com.automobilefleet.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public abstract class ServiceInitialSetup {

    abstract void shouldReturnSingleList();
    abstract void shouldReturnById();
    abstract void shouldSave();
    abstract void shouldUpdate();
    abstract void shouldThrowErros();
}