package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostumerRequest {

    @JsonProperty("costumer_name")
    private String name;

    @JsonProperty("costumer_bd")
    private LocalDate birthDate;

    @JsonProperty("costumer_email")
    private String email;

    @JsonProperty("drive_license")
    private String driveLicense;

    @JsonProperty("address")
    private String address;

    @JsonProperty("costumer_phone_number")
    private String phone;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("update_at")
    private LocalDateTime updateAt;
}
