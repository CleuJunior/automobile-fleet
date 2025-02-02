package br.com.producer.comment_producer.dto.request;

import java.util.UUID;

public record CarCommentRequest(
        String name,
        String carId,
        String comment,
        int rating
) {
}

