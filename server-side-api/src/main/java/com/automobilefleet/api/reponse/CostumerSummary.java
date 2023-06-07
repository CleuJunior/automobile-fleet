package com.automobilefleet.api.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CostumerSummary {

    @JsonProperty("costumer_name")
    private String name;

    @JsonProperty("costumer_email")
    private String email;

    @JsonProperty("costumer_phone_number")
    private String phone;

    @JsonProperty("drive_license")
    private String driveLicense;

    @JsonProperty("address")
    private String address;

}
