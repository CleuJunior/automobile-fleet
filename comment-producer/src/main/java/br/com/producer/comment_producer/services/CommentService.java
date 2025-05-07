package br.com.producer.comment_producer.services;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import br.com.producer.comment_producer.dto.request.CarCommentResponse;
import br.com.producer.comment_producer.entities.CarComment;
import br.com.producer.comment_producer.repositories.CarCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CarCommentRepository carCommentRepository;
    private final QueueService queueService;

    public void sendCarComment(CarCommentRequest carCommentRequest) {
        queueService.sendMessage(carCommentRequest);
    }

    public CarCommentResponse saveCarComment(CarComment comment) {
        var carComment = carCommentRepository.save(comment);

        return CarCommentResponse.of(carComment);
    }
}

