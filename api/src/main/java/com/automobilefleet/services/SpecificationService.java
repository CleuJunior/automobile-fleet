package com.automobilefleet.services;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface SpecificationService {

    List<SpecificationResponse> listSpecifications();

    Page<SpecificationResponse> pageSpecification(int page, int size);

    SpecificationResponse getSpecificationById(UUID id);

    SpecificationResponse saveSpecification(SpecificationRequest request);

    SpecificationResponse updateSpecification(UUID id, SpecificationRequest request);
}