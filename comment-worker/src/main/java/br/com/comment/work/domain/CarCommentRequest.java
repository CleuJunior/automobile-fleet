package br.com.comment.work.domain;

public record CarCommentRequest(
        String name,
        String carId,
        String comment,
        int rating
) {
}

