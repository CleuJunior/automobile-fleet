package br.com.comment.work.service;

import br.com.comment.work.domain.CarCommentRequest;
import br.com.comment.work.domain.CarCommentResponse;
import feign.Headers;
import feign.RequestLine;

public interface CarCommentService {

    @RequestLine("POST /api/v1/car-comments")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    CarCommentResponse addCarComment(CarCommentRequest request);

}
