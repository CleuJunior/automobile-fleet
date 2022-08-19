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

    public List<CostumerResponse> listCostumer() {
        List<Costumer> costumers = repository.findAll();

        return CostumerMapper.toCostumerResponseList(costumers);

    }

    public CostumerResponse getCostumer(Long id) {
        Costumer response = repository.findById(id).get();

        return CostumerMapper.toCostumerResponse(response);
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer costumerSave = CostumerMapper.toCostumer(request);
        repository.save(costumerSave);

        return CostumerMapper.toCostumerResponse(costumerSave);
    }

    public CostumerResponse updateCostumer(Long id, CostumerRequest request) {
        Costumer response = repository.findById(id).get();
        CostumerMapper.updateCostumer(response, request);

        repository.save(response);

        return CostumerMapper.toCostumerResponse(response);
    }

    public void deleteCostumer(Long id) {
        repository.deleteById(id);
    }
}
