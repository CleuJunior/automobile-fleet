package com.automobilefleet.api.dto.request;

import java.util.UUID;

public record CarCommentRequest(
        String name,
        UUID carId,
        String comment,
        int rating
) { }
