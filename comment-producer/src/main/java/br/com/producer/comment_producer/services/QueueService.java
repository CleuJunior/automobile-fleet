package br.com.producer.comment_producer.services;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final SerializationService serialization;

    @Value("${aws.queue.url.car}")
    private String carUrlQueue;

    public void sendMessage(CarCommentRequest carCommentRequest) {
        var body = serialization.toJson(carCommentRequest);
        queueMessagingTemplate.send(carUrlQueue, MessageBuilder.withPayload(body).build());
    }
}
