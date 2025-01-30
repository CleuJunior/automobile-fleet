package com.automobilefleet.mapper;

import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.PageRequest.of;

@Component
public class SpecificationMapper {

    public SpecificationResponse toSpecificationResponse(Specification spec) {
        return new SpecificationResponse(spec.getId(), spec.getName(), spec.getDescription(), spec.getCreatedAt());
    }

    public List<SpecificationResponse> toSpecificationResponseList(List<Specification> specifications) {
        return specifications.stream()
                .filter(Objects::nonNull)
                .map(this::toSpecificationResponse)
                .toList();
    }

    public Page<SpecificationResponse> toSpecificationResponsePage(Page<Specification> specifications, int page, int size) {
        var total = specifications.getTotalElements();
        var response = toSpecificationResponseList(specifications.getContent());

        return new PageImpl<>(response, of(page, size), total);
    }

    public Specification apply(Specification current, SpecificationRequest request) {
        current.setName(request.name());
        current.setDescription(request.description());

        return current;
    }
}
