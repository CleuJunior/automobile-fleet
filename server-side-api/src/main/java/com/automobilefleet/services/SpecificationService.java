package com.automobilefleet.services;

import com.automobilefleet.api.reponse.SpecificationResponse;
import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.exceptions.SpecificationNotFoundException;
import com.automobilefleet.repositories.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecificationService {

    private final SpecificationRepository repository;
    private final ModelMapper mapper;

    public List<SpecificationResponse> listSpecifications() {
        return this.repository.findAll().stream()
                .map(specification -> this.mapper.map(specification, SpecificationResponse.class))
                .collect(Collectors.toList());
    }

    public SpecificationResponse getSpecification(Long id) {
        Specification response = this.repository.findById(id).
                orElseThrow(SpecificationNotFoundException::new);

        return this.mapper.map(response, SpecificationResponse.class);
    }

    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        Specification response = this.mapper.map(request, Specification.class);
        response = repository.save(response);

        return this.mapper.map(response, SpecificationResponse.class);
    }

    public SpecificationResponse updateSpecification(Long id, SpecificationRequest request) {
        Specification response = this.repository.findById(id).
                orElseThrow(SpecificationNotFoundException::new);

        response.setName(request.getName());
        response.setDescription(request.getDescription());
        response = this.repository.save(response);

        return this.mapper.map(response, SpecificationResponse.class);
    }

    public void deleteSpecification(Long id) {
        Specification response = this.repository.findById(id).
                orElseThrow(SpecificationNotFoundException::new);

        this.repository.delete(response);
    }
}