package com.automobilefleet.util;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CostumerUtils {

    public static void updateCostumer(Costumer costumer, CostumerRequest request) {
        costumer.setName(request.getName());
        costumer.setBirthDate(request.getBirthDate());
        costumer.setEmail(request.getEmail());
        costumer.setDriverLicense(request.getDriverLicense());
        costumer.setAddress(request.getAddress());
        costumer.setPhone(request.getPhone());
    }

}
