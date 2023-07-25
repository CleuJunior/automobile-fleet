package com.automobilefleet.services;

import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.api.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.notfoundexception.SpecificationNotFoundException;
import com.automobilefleet.repositories.SpecificationRepository;
import com.automobilefleet.util.mapper.SpecificationMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
        Specification response = this.repository.findById(id).
                orElseThrow(SpecificationNotFoundException::new);

        return SpecificationMapperUtils.toSpecificationReponse(response);
    }

    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        Specification response = SpecificationMapperUtils.toSpecification(request);
        return SpecificationMapperUtils.toSpecificationReponse(this.repository.save(response));
    }

    public SpecificationResponse updateSpecification(UUID id, SpecificationRequest request) {
        Specification response = this.repository.findById(id).
                orElseThrow(SpecificationNotFoundException::new);

        response.setName(request.getName());
        response.setDescription(request.getDescription());
        return SpecificationMapperUtils.toSpecificationReponse(this.repository.save(response));
    }
}