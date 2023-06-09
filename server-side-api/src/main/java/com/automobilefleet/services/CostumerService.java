package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.CostumerNotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.util.CostumerUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CostumerService {

    private final CostumerRepository repository;
    private final ModelMapper mapper;

    public List<CostumerResponse> listCostumer() {
        return this.repository.findAll().stream()
                .map(customer -> this.mapper.map(customer,CostumerResponse.class))
                .collect(Collectors.toList());
    }

    public CostumerResponse getCostumerByName(String name) {
        Costumer response = this.repository.findByName(name)
                .orElseThrow(CostumerNotFoundException::new);

        return this.mapper.map(response,CostumerResponse.class);
    }

    public List<CostumerResponse> findListNamesLike(String name) {
        return this.repository.findByNameListLike(name).stream()
                .map(costumer -> this.mapper.map(costumer, CostumerResponse.class))
                .collect(Collectors.toList());
    }

    public CostumerResponse getCostumerById(Long id) {
        Costumer response = this.repository.findById(id)
                .orElseThrow(CostumerNotFoundException::new);

        return this.mapper.map(response, CostumerResponse.class);
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer response = this.mapper.map(request, Costumer.class);
        response = repository.save(response);

        return this.mapper.map(response, CostumerResponse.class);
    }

    public CostumerResponse updateCostumer(Long id, CostumerRequest request) {
        Costumer response = this.repository.findById(id)
                .orElseThrow(CostumerNotFoundException::new);

        CostumerUtils.updateCostumer(response, request);
        this.repository.save(response);

        return  this.mapper.map(response, CostumerResponse.class);
    }

}
