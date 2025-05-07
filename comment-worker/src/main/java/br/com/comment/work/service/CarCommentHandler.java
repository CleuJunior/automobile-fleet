package br.com.comment.work.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CarCommentHandler {

    private final Converter converter;
    private final CarCommentService carCommentService;

    public void handle(String body) {
        var event = converter.carCommentRequest(body);

        carCommentService.addCarComment(event);
        log.info("Item {} saved on MongoDB", event);

    }
}
