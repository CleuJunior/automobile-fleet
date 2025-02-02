package br.com.producer.comment_producer.dto.request;

public record CarCommentRequest(
        String name,
        String carId,
        String comment,
        int rating
) {
}

