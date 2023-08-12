package com.automobilefleet.api.controllers;

import br.com.six2six.fixturefactory.Fixture;
import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.services.SpecificationService;
import com.automobilefleet.utils.JsonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

class SpecificationControllerTest extends ControllerLayerTest {
    @Mock
    private SpecificationService service;
    private SpecificationResponse response;
    private SpecificationRequest request;
    private static final UUID ID = UUID.fromString("6b83e4cd-ead6-4af0-8e1e-4c332a842717");

    // Endpoints
    private final static String URL_ID = "/api/v1/specification/{id}";
    private final static String URL_LIST = "/api/v1/specification/list";
    private final static String URL_SAVE = "/api/v1/specification/save";
    private final static String UPDATE_ID = "/api/v1/specification/update/{id}";
    private final static String CONTENT_STRING_JSON =
            "{\"_id\":\"6b83e4cd-ead6-4af0-8e1e-4c332a842717\",\"name\":\"Motor\",\"description\":\"Especificação técnica que define o tipo e a potência do motor do veículo.\"}";
    private final static String CONTENT_STRING_JSON_LIST =
            "[{\"_id\":\"6b83e4cd-ead6-4af0-8e1e-4c332a842717\",\"name\":\"Motor\",\"description\":\"Especificação técnica que define o tipo e a potência do motor do veículo.\"}]";

    @BeforeEach
    void setupAttributes() {
        SpecificationController controller = new SpecificationController(this.service);
        super.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Specification specification = Fixture.from(Specification.class).gimme("specification");
        this.response = new SpecificationResponse(specification);
        this.request = new SpecificationRequest(specification.getName(), specification.getDescription());
    }

    @Override @Test
    void shouldGetByIdAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.getSpecification(ID)).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(get(URL_ID, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, super.httpResponse.getStatus());

        Mockito.verify(this.service).getSpecification(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Override @Test
    void shouldGetSingleListAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.listSpecifications()).thenReturn(Collections.singletonList(this.response));

        super.httpResponse = super.mockMvc.perform(get(URL_LIST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON_LIST, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, httpResponse.getStatus());

        Mockito.verify(this.service).listSpecifications();
        Mockito.verifyNoMoreInteractions(this.service);

    }

    @Override @Test
    void shoulSaveAndStatusCodeCreated() throws Exception {
        Mockito.when(this.service.saveSpecification(any(SpecificationRequest.class))).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.response)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_IS_CREATED, httpResponse.getStatus());

        Mockito.verify(this.service).saveSpecification(any(SpecificationRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);

    }

    @Override @Test
    void shouldUpdateAndStatusCodeAccepted() throws Exception {
        Mockito.when(this.service.updateSpecification(eq(ID), any(SpecificationRequest.class))).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.request)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_IS_ACCEPETD, httpResponse.getStatus());

        Mockito.verify(this.service).updateSpecification(eq(ID), any(SpecificationRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }
}