package com.automobilefleet.services;

import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.util.mapper.CostumerMapperUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CostumerService {
    private static final Logger LOG = LoggerFactory.getLogger(CostumerService.class);
    private final CostumerRepository repository;

    public List<CostumerResponse> listCostumer() {
       LOG.info("List costumer registered!");

        return this.repository.findAll().stream()
                .map(CostumerMapperUtils::toCostumerReponse)
                .collect(Collectors.toList());
    }

    public CostumerResponse getCostumerById(UUID id) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        LOG.info("Costumer found successfully!");
        return CostumerMapperUtils.toCostumerReponse(response.get());
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer response = CostumerMapperUtils.toCostumer(request);
        LOG.info("Costumer saved!");
        return CostumerMapperUtils.toCostumerReponse(this.repository.save(response));
    }

    public CostumerResponse updateCostumer(UUID id, CostumerRequest request) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new NotFoundException(ExceptionsConstants.COSTUMER_NOT_FOUND);
        }

        CostumerMapperUtils.updateCostumer(response.get(), request);
        LOG.info("Costumer updated!");
        return CostumerMapperUtils.toCostumerReponse(this.repository.save(response.get()));
    }
}