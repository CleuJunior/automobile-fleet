package br.com.producer.comment_producer.services;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarCommentService {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${aws.queue.url}")
    private String queueUrl;

    public void sendComment(CarCommentRequest carCommentRequest) {
        queueMessagingTemplate.send(queueUrl, MessageBuilder.withPayload(body).build());
    }
}

