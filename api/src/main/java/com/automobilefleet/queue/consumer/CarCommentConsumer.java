package com.automobilefleet.queue.consumer;

import com.automobilefleet.api.dto.request.CarCommentRequest;
import com.automobilefleet.entities.CarComment;
import com.automobilefleet.repositories.CarCommentRepository;
import com.automobilefleet.repositories.CarRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CarCommentConsumer {

    private final CarCommentRepository carCommentRepository;
    private final CarRepository carRepository;

    @SqsListener("${aws.sqs.name}")
    public void receiveMessage(CarCommentRequest request) {
        log.info("Comment {} in on the queue", request);

        var car = carRepository.findById(request.carId()).orElseThrow();

        var comment = CarComment.builder()
                .name(request.name())
                .car(car)
                .comment(request.comment())
                .rating(request.rating())
                .build();

        carCommentRepository.save(comment);
        log.info("Comment from {} saved", comment.getName());
    }
}
