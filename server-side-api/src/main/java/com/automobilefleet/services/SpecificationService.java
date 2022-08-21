package com.automobilefleet.services;

import com.automobilefleet.api.mapper.SpecificationMapper;
import com.automobilefleet.api.reponse.SpecificationResponse;
import com.automobilefleet.api.request.SpecificationRequest;
import com.automobilefleet.entities.Specification;
import com.automobilefleet.repositories.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepository repository;

    public List<SpecificationResponse> listSpecifications() {
        List<Specification> specifications = repository.findAll();

        return SpecificationMapper.toSpecificationResponseList(specifications);

    }

    public SpecificationResponse getSpecification(Long id) {
        Specification response = repository.findById(id).get();

        return SpecificationMapper.toSpecificationResponse(response);
    }

    public SpecificationResponse saveSpecification(SpecificationRequest request) {
        Specification categorySave = SpecificationMapper.toSpecification(request);
        repository.save(categorySave);

        return SpecificationMapper.toSpecificationResponse(categorySave);
    }

    public SpecificationResponse updateSpecification(Long id, SpecificationRequest request) {
        Specification response = repository.findById(id).get();
        SpecificationMapper.updateSpecification(response, request);

        repository.save(response);

        return SpecificationMapper.toSpecificationResponse(response);
    }

    public void deleteSpecification(Long id) {
        repository.deleteById(id);
    }
}