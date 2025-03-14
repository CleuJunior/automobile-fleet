package br.com.comment.work.service;

import br.com.comment.work.error.RetryLaterException;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RetryableHandler {

    private final CarCommentHandler handler;

    public void tryHandle(SQSMessage sqsMessage) {
        try {
            log.info("trying to handle message {}", sqsMessage.getBody());
            handler.handle(sqsMessage.getBody());
        } catch (RetryLaterException e) {
            log.error("Message cannot be processed now.", e);
        } catch (Exception e) {
            log.error("error processing message", e);
        }
    }
}
