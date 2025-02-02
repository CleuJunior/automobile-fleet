package br.com.producer.comment_producer.services;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final QueueService queueService;

    public void sendCarComment(CarCommentRequest carCommentRequest) {
        queueService.sendMessage(carCommentRequest);
    }
}

