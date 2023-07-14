package com.automobilefleet.services;

import com.automobilefleet.api.response.CostumerResponse;
import com.automobilefleet.api.request.CostumerRequest;
import com.automobilefleet.dao.CostumerJdbcDAO;
import com.automobilefleet.entities.Costumer;
import com.automobilefleet.exceptions.notfoundexception.CostumerNotFoundException;
import com.automobilefleet.repositories.CostumerRepository;
import com.automobilefleet.util.CostumerUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CostumerService {
    private static final Logger LOG = LoggerFactory.getLogger(CostumerService.class);
    private final CostumerRepository repository;
    private final CostumerJdbcDAO costumerDao;
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
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new CostumerNotFoundException();
        }

        return this.mapper.map(response.get(), CostumerResponse.class);
    }

    public CostumerResponse saveCostumer(CostumerRequest request) {
        Costumer response = this.mapper.map(request, Costumer.class);

        response = repository.save(response);
        LOG.info("Costumer saved!");

        return this.mapper.map(response, CostumerResponse.class);
    }

    public CostumerResponse updateCostumer(Long id, CostumerRequest request) {
        Optional<Costumer> response = this.repository.findById(id);

        if (response.isEmpty()) {
            LOG.error("Costumer not found!");
            throw new CostumerNotFoundException();
        }

        CostumerUtils.updateCostumer(response.get(), request);
        this.repository.save(response.get());
        LOG.info("Costumer updated!");
        return  this.mapper.map(response, CostumerResponse.class);
    }

}
