package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CostumerRequest {

    @JsonProperty("name")
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("birth_date")
    @Past
    private LocalDate birthDate;

    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("driver_license")
    @NotBlank
    @Size(min = 11, max = 11)
    private String driverLicense;

    @JsonProperty("address")
    @NotBlank
    @Size(min = 8, max = 255)
    private String address;

    @JsonProperty("phone_number")
    @Pattern(regexp= "^\\(?\\d{2}\\)?[\\s-]?[\\s9]?\\d{5}-?\\d{4}$", message= "Phone format must be (12) 12345-1234")
    private String phone;

}
