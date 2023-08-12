package com.automobilefleet.api.controllers;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Transactional
public abstract class ControllerLayerTest {
    protected MockMvc mockMvc;
    protected MockHttpServletResponse httpResponse;
    protected final static String CHARACTER_ENCODING_UTF_8 = "UTF-8";
    protected final static int HTTP_STATUS_OK = 200;
    protected final static int HTTP_STATUS_IS_CREATED = 201;
    protected final static int HTTP_STATUS_IS_ACCEPETD = 202;


    @BeforeAll
    static void setup() {
        FixtureFactoryLoader.loadTemplates("com.automobilefleet.utils");
    }

    abstract void shouldGetByIdAndStatusCodeOK() throws Exception;
    abstract void shouldGetSingleListAndStatusCodeOK() throws Exception;
    abstract void shoulSaveAndStatusCodeCreated() throws Exception;
    abstract void shouldUpdateAndStatusCodeAccepted() throws Exception;
}