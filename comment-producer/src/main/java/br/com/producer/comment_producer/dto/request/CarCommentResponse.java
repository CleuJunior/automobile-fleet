package br.com.producer.comment_producer.dto.request;

import br.com.producer.comment_producer.entities.CarComment;
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

        public static CarCommentResponse of(CarComment carComment) {
                return new CarCommentResponse(
                        carComment.getId(),
                        carComment.getName(),
                        carComment.getCarId(),
                        carComment.getComment(),
                        carComment.getRating(),
                        carComment.getCreationDate(),
                        carComment.getLastModifiedDate());
        }
}
