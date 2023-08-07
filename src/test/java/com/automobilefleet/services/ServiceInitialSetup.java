package com.automobilefleet.services;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public abstract class ServiceInitialSetup<E, R> {
    @Mock
    protected ModelMapper mapper;
    protected E entity;
    protected R response;

    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    public abstract void shouldReturnSingleList();
    public abstract void shouldReturnById();
    public abstract void shouldSave();
    public abstract void shouldUpdate();
}