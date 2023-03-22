package com.automobilefleet.services;

import com.automobilefleet.api.reponse.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.CostumerNotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

        response.setName(response.getName());
        response.setBirthDate(response.getBirthDate());
        response.setEmail(request.getEmail());
        response.setDriveLicense(response.getDriveLicense());
        response.setDriveLicense(request.getDriveLicense());
        response.setAddress(response.getAddress());
        response.setPhone(request.getPhone());
        response.setUpdateAt(LocalDateTime.now());

        this.repository.save(response);

        return  this.mapper.map(response, CostumerResponse.class);
    }

    public void deleteCostumer(Long id) {
        Costumer costumer = this.repository.findById(id)
                .orElseThrow(CostumerNotFoundException::new);

        this.repository.delete(costumer);
    }
}
