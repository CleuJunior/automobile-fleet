package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.CarResponse;
import com.automobilefleet.api.reponse.CarSummary;
import com.automobilefleet.api.request.CarRequest;
import com.automobilefleet.entities.Car;

import java.util.ArrayList;
import java.util.List;


public class CarMapper {

    private CarMapper() { }

    public static Car toCar(CarRequest request) {
        Car car = new Car();
        car.setName(request.getName());
        car.setDescription(request.getDescription());
        car.setDailyRate(request.getDailyRate());
        car.setAvaliable(request.getAvaliable());
        car.setLicensePlate(request.getLicensePlate());
        car.setBrand(request.getBrand());
        car.setCategory(request.getCategory());
        car.setColor(request.getColor());

        return car;
    }

    public static CarResponse toCarResponse(Car car) {
        CarResponse response = new CarResponse();
        response.setId(car.getId());
        response.setName(car.getName());
        response.setDescription(car.getDescription());
        response.setDailyRate(car.getDailyRate());
        response.setAvaliable(car.getAvaliable());
        response.setLicensePlate(car.getLicensePlate());
        response.setBrand(car.getBrand());
        response.setCategory(car.getCategory());
        response.setColor(car.getColor());
        response.setCreatedAt(car.getCreatedAt());

        return response;
    }

    public static List<CarResponse> toCarResponseList(List<Car> cars) {
        List<CarResponse> response = new ArrayList<>();
        cars.forEach(category -> response.add(toCarResponse(category)));

        return response;
    }

    public static void updateCar(Car car, CarRequest request) {
        car.setName(request.getName());
        car.setDescription(request.getDescription());
        car.setDailyRate(request.getDailyRate());
        car.setAvaliable(request.getAvaliable());
        car.setLicensePlate(request.getLicensePlate());
        car.setBrand(request.getBrand());
        car.setCategory(request.getCategory());
        car.setColor(request.getColor());
    }

    public static CarSummary carSummary(Car car) {
        CarSummary response = new CarSummary();
        response.setName(car.getName());
        response.setLicensePlate(car.getLicensePlate());
        response.setColor(car.getColor());

        return response;

    }
}
