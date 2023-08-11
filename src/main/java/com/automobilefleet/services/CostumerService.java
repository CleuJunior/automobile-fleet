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

    public List<CostumerResponse> listCostumer() {
       LOG.info("List costumer registered!");

        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(CostumerResponse::new)
                .toList();
    }

    public CostumerResponse getCostumerById(UUID id) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        LOG.info("Costumer found successfully!");
        return new CostumerResponse(response.get());
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer response = Costumer.builder()
                .name(request.name())
                .birthdate(request.birthdate())
                .email(request.email())
                .driverLicense(request.driverLicense())
                .address(request.address())
                .phone(request.phone())
                .build();

        LOG.info("Costumer saved!");
        return new CostumerResponse(this.repository.save(response));
    }

    public CostumerResponse updateCostumer(UUID id, CostumerRequest request) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        this.updateCostumer(response.get(), request);
        LOG.info("Costumer updated!");
        return new CostumerResponse(this.repository.save(response.get()));
    }

    private void updateCostumer(Costumer costumer, CostumerRequest request) {
        costumer.setName(request.name());
        costumer.setBirthdate(request.birthdate());
        costumer.setEmail(request.email());
        costumer.setDriverLicense(request.driverLicense());
        costumer.setAddress(request.address());
        costumer.setPhone(request.phone());
    }
}