package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.ExceptionsConstants;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.repositories.SpecificationRepository;
import com.automobilefleet.util.mapper.SpecificationMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepository repository;

    public List<SpecificationResponse> listSpecifications() {
        return this.repository.findAll().stream()
                .map(SpecificationMapperUtils::toSpecificationReponse)
                .collect(Collectors.toList());
    }

    public SpecificationResponse getSpecification(UUID id) {
        Optional<Specification> response = this.repository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        return SpecificationMapperUtils.toSpecificationReponse(response.get());
    }

    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        Specification response = SpecificationMapperUtils.toSpecification(request);
        return SpecificationMapperUtils.toSpecificationReponse(this.repository.save(response));
    }

    public SpecificationResponse updateSpecification(UUID id, SpecificationRequest request) {
        Optional<Specification> response = this.repository.findById(id);

        if (response.isEmpty())
            throw new NotFoundException(ExceptionsConstants.SPECIFICATION_NOT_FOUND);

        response.get().setName(request.getName());
        response.get().setDescription(request.getDescription());
        return SpecificationMapperUtils.toSpecificationReponse(this.repository.save(response.get()));
    }
}