package com.automobilefleet.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class CostumerRequest {

    @JsonProperty("name")
    @NotBlank(message = "Name can't be blank!")
    @Size(min = 2, max = 255, message = "Name must contain between 2 to 255 characters!")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("birthdate")
    @Past(message = "Entered date is incorrect!")
    private LocalDate birthdate;

    @JsonProperty("email")
    @Email(message = "Must be a well-formed email address!")
    private String email;

    @JsonProperty("driver_license")
    @NotBlank(message = "Driver's license can't be blank!")
    @Size(min = 11, max = 11, message = "Driver's license length must be 11!")
    private String driverLicense;

    @JsonProperty("address")
    @NotBlank(message = "Address can't be blank!")
    @Size(min = 8, max = 255, message = "Address must contain between 8 to 255 characters!")
    private String address;

    @JsonProperty("phone_number")
    @Pattern(regexp= "^\\(?\\d{2}\\)?[\\s-]?[\\s9]?\\d{5}-?\\d{4}$", message= "Phone format must be (12) 12345-1234")
    private String phone;

}
