package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepository repository;
    private final ModelMapper mapper;

    public List<SpecificationResponse> listSpecifications() {
        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(specification -> this.mapper.map(specification, SpecificationResponse.class))
                .collect(Collectors.toList());
    }

    public SpecificationResponse getSpecification(UUID id) {
        Optional<Specification> response = this.repository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        return this.mapper.map(response.get(), SpecificationResponse.class);
    }

    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        Specification response = Specification
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return this.mapper.map(this.repository.save(response), SpecificationResponse.class);
    }

    public SpecificationResponse updateSpecification(UUID id, SpecificationRequest request) {
        Optional<Specification> response = this.repository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        response.get().setName(request.getName());
        response.get().setDescription(request.getDescription());
        return this.mapper.map(this.repository.save(response.get()), SpecificationResponse.class);
    }
}