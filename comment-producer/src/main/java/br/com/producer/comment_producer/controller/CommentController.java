package br.com.producer.comment_producer.controller;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import br.com.producer.comment_producer.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService carComment;

    @PostMapping("/cars/add-comment")
    @ResponseStatus(OK)
    public void addCarComment(@RequestBody CarCommentRequest carCommentRequest) {
        carComment.sendCarComment(carCommentRequest);
    }
}
