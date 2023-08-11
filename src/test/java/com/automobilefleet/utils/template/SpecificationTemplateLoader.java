package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.api.dto.request.SpecificationRequest;
import com.automobilefleet.api.dto.response.SpecificationResponse;
import com.automobilefleet.entities.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class SpecificationTemplateLoader implements TemplateLoader {
    private static final UUID ID = UUID.fromString("6b83e4cd-ead6-4af0-8e1e-4c332a842717");
    private static final String NAME = "Motor";
    private static final String DESCRIPTION = "Especificação técnica que define o tipo e a potência do motor do veículo.";

    @Override
    public void load() {
        Fixture.of(Specification.class).addTemplate("specification", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("description", DESCRIPTION);
        }});
    }
}