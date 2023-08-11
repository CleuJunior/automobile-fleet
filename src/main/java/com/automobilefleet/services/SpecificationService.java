package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepository repository;

    public List<SpecificationResponse> listSpecifications() {
        return this.repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(SpecificationResponse::new)
                .toList();
    }

    public SpecificationResponse getSpecification(UUID id) {
        Optional<Specification> response = this.repository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        return new SpecificationResponse(response.get());
    }

    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        Specification response = Specification
                .builder()
                .name(request.name())
                .description(request.description())
                .build();

        return new SpecificationResponse(this.repository.save(response));
    }

    public SpecificationResponse updateSpecification(UUID id, SpecificationRequest request) {
        Optional<Specification> response = this.repository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        response.get().setName(request.name());
        response.get().setDescription(request.description());
        return new SpecificationResponse(this.repository.save(response.get()));
    }
}