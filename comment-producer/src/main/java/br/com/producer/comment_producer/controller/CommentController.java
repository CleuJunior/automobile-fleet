package br.com.producer.comment_producer.controller;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import br.com.producer.comment_producer.services.CarCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
@RequiredArgsConstructor
public class CommentController {

    private final CarCommentService carComment;

    @PostMapping("/cars/add-comment")
    @ResponseStatus(HttpStatus.OK)
    public void search(@RequestBody CarCommentRequest carCommentRequest) {
        carComment.sendComment(carCommentRequest);
    }


}
