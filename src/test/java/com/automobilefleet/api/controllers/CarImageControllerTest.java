package com.automobilefleet.api.controllers;

import br.com.six2six.fixturefactory.Fixture;
import com.automobilefleet.api.dto.request.CarImageRequest;
import com.automobilefleet.api.dto.response.CarImageResponse;
import com.automobilefleet.entities.CarImage;
import com.automobilefleet.services.CarImageService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class CarImageControllerTest extends ControllerLayerTest {
    @Mock
    private CarImageService service;
    private CarImageResponse response;
    private CarImageRequest request;
    private static final UUID ID = UUID.fromString("4bfad864-59e7-4fc3-b45e-3886694b3717");

    // Endpoints
    private final static String URL_ID = "/api/v1/car-images/{id}";
    private final static String URL_LIST = "/api/v1/car-images/list";
    private final static String URL_SAVE = "/api/v1/car-images/save";
    private final static String UPDATE_ID = "/api/v1/car-images/update/{id}";
    private final static String DELETE_ID = "/api/v1/car-images/delete/{id}";
    private final static String CONTENT_STRING_JSON = "{\"_id\":\"4bfad864-59e7-4fc3-b45e-3886694b3717\",\"image\":\"89504E470D0A1A0A0000000D49484452000000\",\"car\":{\"_id\":\"4dafc4f4-5e90-478d-a386-841d74aa368a\",\"name\":\"Civic\",\"description\":\"Sedan médio da Honda\",\"daily_rate\":110.0,\"available\":true,\"license_plate\":\"OPQ-7890\",\"brand\":{\"_id\":\"3f831dbb-de3b-4b1a-95dc-602cdeaa7012\",\"name\":\"Honda\"},\"category\":{\"_id\":\"dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d\",\"name\":\"Carros Antigos\",\"description\":\"Veículos com mais de 30 anos de fabricação\"},\"color\":\"Preto\"}}";
    private final static String CONTENT_STRING_JSON_LIST = "[{\"_id\":\"4bfad864-59e7-4fc3-b45e-3886694b3717\",\"image\":\"89504E470D0A1A0A0000000D49484452000000\",\"car\":{\"_id\":\"4dafc4f4-5e90-478d-a386-841d74aa368a\",\"name\":\"Civic\",\"description\":\"Sedan médio da Honda\",\"daily_rate\":110.0,\"available\":true,\"license_plate\":\"OPQ-7890\",\"brand\":{\"_id\":\"3f831dbb-de3b-4b1a-95dc-602cdeaa7012\",\"name\":\"Honda\"},\"category\":{\"_id\":\"dc915ab8-aa4f-4dbd-bd09-94f8c12ff52d\",\"name\":\"Carros Antigos\",\"description\":\"Veículos com mais de 30 anos de fabricação\"},\"color\":\"Preto\"}}]";

    @BeforeEach
    void setupAttributes() {
        CarImageController controller = new CarImageController(this.service);
        super.mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();

        CarImage carImage = Fixture.from(CarImage.class).gimme("car image");
        this.response = new CarImageResponse(carImage);
        this.request = new CarImageRequest(carImage.getCar().getId(), carImage.getLinkImage());
    }

    @Override @Test
    void shouldGetByIdAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.getImageById(ID)).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(get(URL_ID, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, super.httpResponse.getStatus());

        Mockito.verify(this.service).getImageById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Override @Test
    void shouldGetSingleListAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.listAllImage()).thenReturn(Collections.singletonList(this.response));

        super.httpResponse = super.mockMvc.perform(get(URL_LIST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON_LIST, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, httpResponse.getStatus());

        Mockito.verify(this.service).listAllImage();
        Mockito.verifyNoMoreInteractions(this.service);

    }

    @Override @Test
    void shoulSaveAndStatusCodeCreated() throws Exception {
        Mockito.when(this.service.saveCarImage(any(CarImageRequest.class))).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.response)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_IS_CREATED, httpResponse.getStatus());

        Mockito.verify(this.service).saveCarImage(any(CarImageRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);

    }

    @Override @Test
    void shouldUpdateAndStatusCodeAccepted() throws Exception {
        Mockito.when(this.service.updateCarImage(eq(ID), any(CarImageRequest.class))).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.request)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_IS_ACCEPETD, httpResponse.getStatus());

        Mockito.verify(this.service).updateCarImage(eq(ID), any(CarImageRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldDeleteAndStatusCodeNoContent() throws Exception {
        super.mockMvc.perform(delete(DELETE_ID, ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        Mockito.verify(this.service).deleteCarImageById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }
}