package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CostumerRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("driver_license")
    private String driverLicense;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone_number")
    private String phone;

}
