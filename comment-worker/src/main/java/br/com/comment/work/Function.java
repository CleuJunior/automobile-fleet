package br.com.comment.work;

import br.com.comment.work.config.HttpErrorDecoder;
import br.com.comment.work.service.CarCommentHandler;
import br.com.comment.work.service.CarCommentService;
import br.com.comment.work.service.Converter;
import br.com.comment.work.service.RetryableHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static feign.Logger.Level.FULL;

@Slf4j
@AllArgsConstructor
public class Function implements RequestHandler<SQSEvent, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient httpClient = new OkHttpClient();
    private final JacksonEncoder jacksonEncoder = new JacksonEncoder();
    private final JacksonDecoder jacksonDecoder = new JacksonDecoder();
    private final HttpErrorDecoder errorDecoder = new HttpErrorDecoder(objectMapper);
    private final RetryableHandler retryableHandler;

    @SuppressWarnings("WeakerAccess")
    public Function() {
        var handler = new CarCommentHandler(new Converter(), carCommentService());
        this.retryableHandler = new RetryableHandler(handler);
    }

    @Override
    public String handleRequest(SQSEvent event, Context context) {
        log.info("Documents inbox worker triggered: {}", context.getAwsRequestId());
        event.getRecords().forEach(retryableHandler::tryHandle);

        return "Success";
    }

    public CarCommentService carCommentService() {
        return Feign.builder()
                .client(httpClient)
                .encoder(jacksonEncoder)
                .decoder(jacksonDecoder)
                .errorDecoder(errorDecoder)
                .logger(new Slf4jLogger())
                .logLevel(FULL)
                .target(CarCommentService.class, "http://localhost:8081");
    }
}