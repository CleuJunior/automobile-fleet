package com.automobilefleet.api.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"_id"})
public class CostumerResponse {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("costumer_name")
    private String name;

    @JsonProperty("costumer_bd")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
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
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("update_at")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updateAt;
}
