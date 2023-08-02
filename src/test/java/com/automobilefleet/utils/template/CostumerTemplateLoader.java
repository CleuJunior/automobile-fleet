package com.automobilefleet.utils.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automobilefleet.api.dto.request.CostumerRequest;
import com.automobilefleet.api.dto.response.CostumerResponse;
import com.automobilefleet.entities.Costumer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CostumerTemplateLoader implements TemplateLoader {
    private static final UUID ID = UUID.fromString("32ca0461-0401-4b15-bf57-3d2b18b3828f");
    private static final String NAME = "Raimunda Regina Porto";
    private static final LocalDate BIRTHDATE = LocalDate.of(1974, 3, 5);
    private static final String EMAIL = "raimundareginaporto@clinicasilhouette.com.br";
    private static final String DRIVER_LICENSE = "08493447718";
    private static final String ADDRESS = "Rua Astrogildo de Almeida, 328";
    private static final String PHONE = "(73) 99362-1339";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2021, 8, 2, 8, 22, 1);
    private static final LocalDateTime UPDATED_AT = LocalDateTime.of(2022, 12, 24, 21, 29, 21);

    @Override
    public void load() {
        Fixture.of(Costumer.class).addTemplate("costumer", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("birthdate", BIRTHDATE);
            add("email", EMAIL);
            add("driverLicense", DRIVER_LICENSE);
            add("address", ADDRESS);
            add("phone", PHONE);
            add("createdAt", CREATED_AT);
            add("updatedAt", UPDATED_AT);
        }});

        Fixture.of(CostumerResponse.class).addTemplate("response", new Rule() {{
            add("id", ID);
            add("name", NAME);
            add("birthdate", BIRTHDATE);
            add("email", EMAIL);
            add("driverLicense", DRIVER_LICENSE);
            add("address", ADDRESS);
            add("phone", PHONE);
            add("createdAt", CREATED_AT);
            add("updatedAt", UPDATED_AT);
        }});

        Fixture.of(CostumerRequest.class).addTemplate("request", new Rule() {{
            add("name", NAME);
            add("birthdate", BIRTHDATE);
            add("email", EMAIL);
            add("driverLicense", DRIVER_LICENSE);
            add("address", ADDRESS);
            add("phone", PHONE);
        }});
    }
}