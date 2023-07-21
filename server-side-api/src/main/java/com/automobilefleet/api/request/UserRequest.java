package com.automobilefleet.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserRequest {

    @JsonProperty("username")
    @NotBlank(message = "Username can't be blank!")
    @Size(min = 5, max = 255, message = "Username must contain between 5 to 255 characters!")
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "Password can't be blank!")
    @Size(min = 5, message = "Password must contain at least 5 characters!")
    private String password;
}
