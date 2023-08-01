package com.automobilefleet.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class UserResponse {

    @NonNull
    @JsonProperty("user_id")
    private UUID id;

    @NonNull
    @JsonProperty("username")
    private String username;

}