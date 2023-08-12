package com.automobilefleet.api.controllers;

import br.com.six2six.fixturefactory.Fixture;
import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Car;
import com.automobilefleet.services.CarService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class CarControllerTest extends ControllerLayerTest {
    @Mock
    private CarService service;
    private CarResponse response;
    private CarRequest request;
    private static final UUID ID = UUID.fromString("4bfad864-59e7-4fc3-b45e-3886694b3717");

    // Endpoints
    private final static String URL_ID = "/api/v1/cars/{id}";
    private final static String URL_LIST = "/api/v1/cars/list";
    private final static String URL_SAVE = "/api/v1/cars/save";
    private final static String UPDATE_ID = "/api/v1/cars/update/{id}";
    private final static String AVAILABLE = "/api/v1/cars/available";
    private final static String CAR_BY_BRAND = "/api/v1/cars/brand/{brandName}";
    private final static String CONTENT_STRING_JSON = "{\"_id\":\"4f2e3bc7-8522-4543-922c-03480d044e62\",\"name\":\"488\",\"description\":\"Esportivo da Ferrari\",\"daily_rate\":1500.0,\"available\":true,\"license_plate\":\"LMN-3456\",\"brand\":{\"_id\":\"4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe\",\"name\":\"Ferrari\"},\"category\":{\"_id\":\"146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9\",\"name\":\"Coupé\",\"description\":\"Categoria de carros com carroceria coupé\"},\"color\":\"Vermelho\"}";
    private final static String CONTENT_STRING_JSON_LIST = "[{\"_id\":\"4f2e3bc7-8522-4543-922c-03480d044e62\",\"name\":\"488\",\"description\":\"Esportivo da Ferrari\",\"daily_rate\":1500.0,\"available\":true,\"license_plate\":\"LMN-3456\",\"brand\":{\"_id\":\"4f2dd5bb-ae60-41ca-9227-0fb3dacebcbe\",\"name\":\"Ferrari\"},\"category\":{\"_id\":\"146c8a0a-828c-4d4c-bbc3-fdc70bcf38f9\",\"name\":\"Coupé\",\"description\":\"Categoria de carros com carroceria coupé\"},\"color\":\"Vermelho\"}]";

    @BeforeEach
    void setupAttributes() {
        CarController controller = new CarController(this.service);
        super.mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();

        Car car = Fixture.from(Car.class).gimme("car");
        this.response = new CarResponse(car);
        this.request = new CarRequest(
                car.getName(),
                car.getDescription(),
                car.getDailyRate(),
                car.isAvailable(),
                car.getLicensePlate(),
                car.getBrand().getId(),
                car.getCategory().getId(),
                car.getColor()
        );
    }

    @Override @Test
    void shouldGetByIdAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.getCarById(ID)).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(get(URL_ID, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, super.httpResponse.getStatus());

        Mockito.verify(this.service).getCarById(ID);
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Override @Test
    void shouldGetSingleListAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.listOfCars()).thenReturn(Collections.singletonList(this.response));

        super.httpResponse = super.mockMvc.perform(get(URL_LIST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON_LIST, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, super.httpResponse.getStatus());

        Mockito.verify(this.service).listOfCars();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldGetSingleListCarAvailableAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.findByCarAvailable()).thenReturn(Collections.singletonList(this.response));

        super.httpResponse = super.mockMvc.perform(get(AVAILABLE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON_LIST, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, super.httpResponse.getStatus());

        Mockito.verify(this.service).findByCarAvailable();
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Test
    void shouldGetSingleListByBrandAndStatusCodeOK() throws Exception {
        Mockito.when(this.service.findByCarBrand("Ferrari")).thenReturn(Collections.singletonList(this.response));

        super.httpResponse = super.mockMvc.perform(get(CAR_BY_BRAND, "Ferrari")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON_LIST, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_OK, super.httpResponse.getStatus());

        Mockito.verify(this.service).findByCarBrand("Ferrari");
        Mockito.verifyNoMoreInteractions(this.service);
    }

    @Override @Test
    void shoulSaveAndStatusCodeCreated() throws Exception {
        Mockito.when(this.service.saveCar(any(CarRequest.class))).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(post(URL_SAVE).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.response)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_IS_CREATED, super.httpResponse.getStatus());

        Mockito.verify(this.service).saveCar(any(CarRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);

    }

    @Override @Test
    void shouldUpdateAndStatusCodeAccepted() throws Exception {
        Mockito.when(this.service.updateCar(eq(ID), any(CarRequest.class))).thenReturn(this.response);

        super.httpResponse = super.mockMvc.perform(put(UPDATE_ID, ID).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonMapper.asJsonString(this.request)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn()
                .getResponse();

        super.httpResponse.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

        Assertions.assertEquals(CONTENT_STRING_JSON, super.httpResponse.getContentAsString());
        Assertions.assertEquals(HTTP_STATUS_IS_ACCEPETD, super.httpResponse.getStatus());

        Mockito.verify(this.service).updateCar(eq(ID), any(CarRequest.class));
        Mockito.verifyNoMoreInteractions(this.service);
    }
}