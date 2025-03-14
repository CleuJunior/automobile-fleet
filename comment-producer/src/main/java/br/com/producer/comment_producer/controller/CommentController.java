package br.com.producer.comment_producer.controller;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import br.com.producer.comment_producer.dto.request.CarCommentResponse;
import br.com.producer.comment_producer.entities.Brand;
import br.com.producer.comment_producer.entities.Car;
import br.com.producer.comment_producer.entities.CarComment;
import br.com.producer.comment_producer.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/v1/car-comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService carComment;

    @PostMapping("/cars/add-comment")
    @ResponseStatus(OK)
    public void addCarComment(@RequestBody CarCommentRequest carCommentRequest) {
        carComment.sendCarComment(carCommentRequest);
    }

    @PostMapping
    @ResponseStatus(OK)
    public CarCommentResponse saveCarComment(@RequestBody CarCommentRequest commentRequest) {
        var car = new Car(
                "17fb9ae3-b6df-4b58-b8f6-20a8e7e37c0d",
                "Citroen",
                "99813441",
                new Brand("df2a0e36-0c8d-49bc-bd7f-2a7f54de8c9d", "Fox"),
                "Black");

        var comment = CarComment.builder()
                .name(commentRequest.name())
                .carId(commentRequest.carId())
                .car(car)
                .comment(commentRequest.comment())
                .rating(commentRequest.rating())
                .build();

        return carComment.saveCarComment(comment);
    }
}
