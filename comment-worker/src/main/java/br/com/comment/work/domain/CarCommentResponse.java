package br.com.comment.work.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public record CarCommentResponse(
        String id,
        String name,
        String carId,
        String comment,
        int rating,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
        ZonedDateTime creationDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
        ZonedDateTime lastModifiedDate) {

}
