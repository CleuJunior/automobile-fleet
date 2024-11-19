package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CarRequest;
import com.automobilefleet.api.dto.response.CarResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.CarMapper;
import com.automobilefleet.repositories.BrandRepository;
import com.automobilefleet.repositories.CarRepository;
import com.automobilefleet.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.Page.empty;

@ExtendWith(SpringExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CarMapper mapper;
    @Mock
    private Car car;
    @Mock
    private Brand brand;
    @Mock
    private Category category;
    @Mock
    private CarResponse response;
    @Mock
    private CarRequest request;
    @InjectMocks
    private CarServiceImpl service;

    private static final UUID CAR_ID = randomUUID();
    private static final UUID BRAND_ID = randomUUID();
    private static final UUID CATEGORY_ID = randomUUID();
    private static final String BRAND_NAME = "GENERIC_NAME";
    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 1);


    @Test
    void shouldReturnSingleListCar() {
        given(carRepository.findAll()).willReturn(singletonList(car));
        given(mapper.toCarResponseList(singletonList(car))).willReturn(singletonList(response));

        var actual = service.listOfCars();

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(carRepository).findAll();
        verify(mapper).toCarResponseList(singletonList(car));
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListCar() {
        given(carRepository.findAll()).willReturn(emptyList());

        var actual = service.listOfCars();

        then(actual).isEmpty();

        verify(carRepository).findAll();
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnSingleListCallingByName() {
        given(carRepository.findCarsByBrandName(BRAND_NAME)).willReturn(singletonList(car));
        given(mapper.toCarResponseList(singletonList(car))).willReturn(singletonList(response));

        var actual = service.findByCarBrand(BRAND_NAME);

        // Assertions
        then(actual).hasSize(1);
        then(actual).contains(response);

        // Verifications
        verify(carRepository).findCarsByBrandName(BRAND_NAME);
        verify(mapper).toCarResponseList(singletonList(car));
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListCallingByName() {
        given(carRepository.findCarsByBrandName(BRAND_NAME)).willReturn(emptyList());

        var actual = service.findByCarBrand(BRAND_NAME);

        then(actual).isEmpty();

        verify(carRepository).findCarsByBrandName(BRAND_NAME);
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnSingleListCallingByAvailable() {
        given(carRepository.findByAvailable()).willReturn(singletonList(car));
        given(mapper.toCarResponseList(singletonList(car))).willReturn(singletonList(response));

        var actual = service.findByCarAvailable();

        then(actual).hasSize(1);
        then(actual).contains(response);

        // Verifications
        verify(carRepository).findByAvailable();
        verify(mapper).toCarResponseList(singletonList(car));
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptytCallingByAvailable() {
        given(carRepository.findByAvailable()).willReturn(emptyList());

        var actual = service.findByCarAvailable();

        then(actual).isEmpty();

        // Verifications
        verify(carRepository).findByAvailable();
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnPageCar() {
        var pageCar = new PageImpl<>(singletonList(car), PAGE_REQUEST, 1);
        var pageCarResponse = new PageImpl<>(singletonList(response), PAGE_REQUEST, 1);

        given(carRepository.findAll(PAGE_REQUEST)).willReturn(pageCar);
        given(mapper.toCarResponsePage(pageCar, 0, 1)).willReturn(pageCarResponse);

        var actual = service.pageCar(0, 1);

        then(actual).hasSize(1);
        then(actual).contains(response);

        verify(carRepository).findAll(PAGE_REQUEST);
        verify(mapper).toCarResponsePage(pageCar, 0, 1);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyPageCar() {
        given(carRepository.findAll(PAGE_REQUEST)).willReturn(empty());

        var actual = service.pageCar(0, 1);

        then(actual).isEmpty();

        verify(carRepository).findAll(PAGE_REQUEST);
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnCarById() {
        given(carRepository.findById(CAR_ID)).willReturn(of(car));
        given(mapper.toCarResponse(car)).willReturn(response);

        var actual = service.getCarById(CAR_ID);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carRepository).findById(CAR_ID);
        verify(mapper).toCarResponse(car);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shoulThrowErrorWhendReturnCarByIdNonExiting() {
        given(carRepository.findById(CAR_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getCarById(CAR_ID));

        verify(carRepository).findById(CAR_ID);
        verifyNoMoreInteractions(carRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldSaveCar() {
        given(request.brandId()).willReturn(BRAND_ID);
        given(request.categoryId()).willReturn(CATEGORY_ID);

        given(brandRepository.findById(BRAND_ID)).willReturn(of(brand));
        given(categoryRepository.findById(CATEGORY_ID)).willReturn(of(category));

        given(carRepository.save(any(Car.class))).willReturn(car);
        given(mapper.toCarResponse(car)).willReturn(response);

        var actual = service.saveCar(request);

        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carRepository).save((any(Car.class)));
        verify(brandRepository).findById(BRAND_ID);
        verify(categoryRepository).findById(CATEGORY_ID);
        verify(mapper).toCarResponse(car);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
        verifyNoMoreInteractions(categoryRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErroWhenSaveCarWithBrandIdNonExisting() {
        given(request.brandId()).willReturn(BRAND_ID);
        given(brandRepository.findById(BRAND_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.saveCar(request));

        verify(brandRepository).findById(BRAND_ID);
        verifyNoMoreInteractions(categoryRepository);
        verifyNoInteractions(carRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldThrowErroWhenSaveCarWithCategoryIdNonExisting() {
        given(request.brandId()).willReturn(BRAND_ID);
        given(request.categoryId()).willReturn(CATEGORY_ID);

        given(brandRepository.findById(BRAND_ID)).willReturn(Optional.of(brand));
        given(categoryRepository.findById(CATEGORY_ID)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.saveCar(request));

        verify(brandRepository).findById(BRAND_ID);
        verify(categoryRepository).findById(CATEGORY_ID);
        verifyNoMoreInteractions(brandRepository);
        verifyNoMoreInteractions(categoryRepository);
        verifyNoInteractions(carRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldUpdateCar() {
        given(carRepository.findById(CAR_ID)).willReturn(of(car));

        given(request.brandId()).willReturn(BRAND_ID);
        given(request.categoryId()).willReturn(CATEGORY_ID);

        given(brandRepository.findById(BRAND_ID)).willReturn(of(brand));
        given(categoryRepository.findById(CATEGORY_ID)).willReturn(of(category));

        given(carRepository.save(any(Car.class))).willReturn(car);
        given(mapper.toCarResponse(car)).willReturn(response);

        given(mapper.apply(car, request, brand, category)).willReturn(car);

        var actual = service.updateCar(CAR_ID, request);

        // Assertions
        then(actual).isNotNull();
        then(actual).isEqualTo(response);

        verify(carRepository).findById(CAR_ID);
        verify(carRepository).save((any(Car.class)));
        verify(brandRepository).findById(BRAND_ID);
        verify(categoryRepository).findById(CATEGORY_ID);
        verify(mapper).toCarResponse(car);
        verify(mapper).apply(car, request, brand, category);
        verifyNoMoreInteractions(carRepository);
        verifyNoMoreInteractions(brandRepository);
        verifyNoMoreInteractions(categoryRepository);
        verifyNoMoreInteractions(mapper);
    }
}