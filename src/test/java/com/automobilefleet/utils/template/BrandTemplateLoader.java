package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.entities.Brand;

import java.time.LocalDateTime;
import java.util.UUID;

public class BrandTemplateLoader implements TemplateLoader {
    private static final UUID ID = UUID.fromString("0a7d6250-0be5-4036-8f23-33dc1762bed0");
    private static final String NAME = "BMW";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2018, 7, 30, 12, 33, 33);

    @Override
    public void load() {
        Fixture.of(Brand.class).addTemplate("brand", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("createdAt", CREATED_AT);
        }});

        Fixture.of(BrandResponse.class).addTemplate("response", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("createdAt", CREATED_AT);
        }});

        Fixture.of(BrandRequest.class).addTemplate("request", new Rule() {{
            add("name", NAME);
        }});
    }
}