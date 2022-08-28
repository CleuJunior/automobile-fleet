package com.automobilefleet.services;

import com.automobilefleet.api.mapper.CostumerMapper;
import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.repositories.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostumerService {
    private final CostumerRepository repository;
    private final CostumerMapper mapper;

    public List<CostumerResponse> listCostumer() {
        List<Costumer> costumers = repository.findAll();

        return mapper.toCostumerResponseList(costumers);

    }

    public CostumerResponse getCostumer(Long id) {
        Costumer response = repository.findById(id).get();

        return mapper.toCostumerResponse(response);
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer costumerSave = mapper.toCostumer(request);
        repository.save(costumerSave);

        return mapper.toCostumerResponse(costumerSave);
    }

    public CostumerResponse updateCostumer(Long id, CostumerRequest request) {
        Costumer costumer = mapper.toCostumer(request);
        Costumer costumerId = repository.findById(id).get();
        Costumer costumerSaved = repository.save(costumer);
        return  mapper.toCostumerResponse(costumerSaved);

    }

    public void deleteCostumer(Long id) {
        repository.deleteById(id);
    }
}
