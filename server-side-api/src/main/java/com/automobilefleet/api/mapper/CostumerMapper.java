package com.automobilefleet.api.mapper;

import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.reponse.CostumerSummary;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CostumerMapper {

    private final ModelMapper mapper;

    public Costumer toCostumer(CostumerRequest request) {
        return mapper.map(request, Costumer.class);
    }

    public CostumerResponse toCostumerResponse(Costumer costumer) {
        return mapper.map(costumer, CostumerResponse.class);
    }

    public List<CostumerResponse> toCostumerResponseList(List<Costumer> costumers) {
        return costumers.stream()
                .map(this::toCostumerResponse)
                .collect(Collectors.toList());
    }


    public static CostumerSummary costumerSummary(Costumer costumer) {
        CostumerSummary summary = new CostumerSummary();
        summary.setName(costumer.getName());
        summary.setEmail(costumer.getEmail());
        summary.setPhone(costumer.getPhone());
        summary.setDriveLicense(costumer.getDriveLicense());
        summary.setAddress(costumer.getAddress());

        return summary;

    }
}
