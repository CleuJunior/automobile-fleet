package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.api.dto.request.BrandRequest;
import com.automobilefleet.api.dto.request.CategoryRequest;
import com.automobilefleet.api.dto.response.BrandResponse;
import com.automobilefleet.api.dto.response.CategoryResponse;
import com.automobilefleet.entities.Brand;
import com.automobilefleet.entities.Car;
import com.automobilefleet.entities.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryTemplateLoader implements TemplateLoader {
    private static final UUID ID = UUID.fromString("b86a92d8-6908-426e-8316-f72b0c849a4b");
    private static final String NAME = "SUVs";
    private static final String DESCRIPTION = "Veículos utilitários esportivos";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2019, 12, 23, 8, 22, 11);

    @Override
    public void load() {
        Fixture.of(Category.class).addTemplate("category", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("description", DESCRIPTION);
            add("createdAt", CREATED_AT);
        }});

        Fixture.of(CategoryResponse.class).addTemplate("response", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("description", DESCRIPTION);
            add("createdAt", CREATED_AT);
        }});

        Fixture.of(CategoryRequest.class).addTemplate("request", new Rule() {{
            add("name", NAME);
            add("description", DESCRIPTION);
        }});
    }
}