package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.mapper.SpecificationMapper;
import com.automobilefleet.repositories.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SpecificationServiceImpl implements SpecificationService {
    private final ModelMapper modelMapper;

    private final SpecificationRepository repository;
    private final SpecificationMapper mapper;

    @Override
    public List<SpecificationResponse> listSpecifications() {
        var specifications = repository.findAll();

        if (specifications.isEmpty()) {
            log.info("Empty list of specifications");
            return emptyList();
        }

        log.info("Return list of specifications");
        return mapper.toSpecificationResponseList(specifications);
    }

    @Override
    public Page<SpecificationResponse> pageSpecification(int page, int size) {
        var specificationPage = repository.findAll(of(page, size));

        if (specificationPage.isEmpty()) {
            log.info("Empty page of specifications");
            return empty();
        }

        log.info("Return page of specifications with size {}", size);
        return mapper.toSpecificationResponsePage(specificationPage, page, size);
    }

    @Override
    public SpecificationResponse getSpecificationById(UUID id) {
        log.info("Specification id: {}", id);
        return repository.findById(id)
                .map(mapper::toSpecificationResponse)
                .orElseThrow(() -> new NotFoundException("specification.not.found", id));
    }

    @Override
    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        log.info("Specification saved successfully");
        return mapper.toSpecificationResponse(repository.save(new Specification(request)));
    }

    @Override
    public SpecificationResponse updateSpecification(UUID id, SpecificationRequest request) {
        return repository.findById(id)
                .map(current -> mapper.apply(current, request))
                .map(repository::save)
                .map(mapper::toSpecificationResponse)
                .orElseThrow(() -> new NotFoundException("specification.not.found", id));
    }
}