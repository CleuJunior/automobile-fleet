package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.CostumerRequest;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CostumerService {
    private static final Logger LOG = LoggerFactory.getLogger(CostumerService.class);
    private final CostumerRepository repository;
    private final ModelMapper mapper;

    public List<CostumerResponse> listCostumer() {
       LOG.info("List costumer registered!");

        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(costumer -> this.mapper.map(costumer, CostumerResponse.class))
                .toList();
    }

    public CostumerResponse getCostumerById(UUID id) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        LOG.info("Costumer found successfully!");
        return this.mapper.map(response.get(), CostumerResponse.class);
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer response = this.mapper.map(request, Costumer.class);
        LOG.info("Costumer saved!");
        return this.mapper.map(this.repository.save(response), CostumerResponse.class);
    }

    public CostumerResponse updateCostumer(UUID id, CostumerRequest request) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        this.updateCostumer(response.get(), request);
        LOG.info("Costumer updated!");
        return this.mapper.map(this.repository.save(response.get()), CostumerResponse.class);
    }

    private void updateCostumer(Costumer costumer, CostumerRequest request) {
        costumer.setName(request.getName());
        costumer.setBirthdate(request.getBirthdate());
        costumer.setEmail(request.getEmail());
        costumer.setDriverLicense(request.getDriverLicense());
        costumer.setAddress(request.getAddress());
        costumer.setPhone(request.getPhone());
    }
}