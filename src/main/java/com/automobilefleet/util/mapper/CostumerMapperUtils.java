package com.automobilefleet.util.mapper;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CostumerMapperUtils {

    public static Costumer toCostumer(CostumerRequest request) {
        return Costumer.builder()
                .name(request.getName())
                .birthdate(request.getBirthdate())
                .email(request.getEmail())
                .driverLicense(request.getDriverLicense())
                .address(request.getAddress())
                .phone(request.getPhone())
                .build();
    }

    public static CostumerResponse toCostumerReponse(Costumer costumer) {
        return CostumerResponse.builder()
                .id(costumer.getId())
                .name(costumer.getName())
                .birthdate(costumer.getBirthdate())
                .email(costumer.getEmail())
                .driverLicense(costumer.getDriverLicense())
                .address(costumer.getAddress())
                .phone(costumer.getPhone())
                .createdAt(costumer.getCreatedAt())
                .updatedAt(costumer.getUpdatedAt())
                .build();
    }

    public static void updateCostumer(Costumer costumer, CostumerRequest request) {
        costumer.setName(request.getName());
        costumer.setBirthdate(request.getBirthdate());
        costumer.setEmail(request.getEmail());
        costumer.setDriverLicense(request.getDriverLicense());
        costumer.setAddress(request.getAddress());
        costumer.setPhone(request.getPhone());
    }
}