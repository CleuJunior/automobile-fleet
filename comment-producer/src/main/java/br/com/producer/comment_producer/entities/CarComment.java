package br.com.producer.comment_producer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document(collection = "car_comment_entity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarComment {

    @Id
    private String id;
    private String name;
    private String carId;
    private Car car;
    private String comment;
    private int rating;
    @CreatedDate
    private ZonedDateTime creationDate;
    @LastModifiedDate
    private ZonedDateTime lastModifiedDate;

}
