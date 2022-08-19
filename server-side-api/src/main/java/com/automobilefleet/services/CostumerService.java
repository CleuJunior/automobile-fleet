package com.automobilefleet.services;

import com.automobilefleet.api.mapper.CostumerMapper;
import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.repositories.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerService {
    private final CostumerRepository repository;

    public Costumer getCostumerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer costumerSave = CostumerMapper.toCostumer(request);
        this.repository.save(costumerSave);

        return CostumerMapper.toCostumerResponse(costumerSave);

    }
}
