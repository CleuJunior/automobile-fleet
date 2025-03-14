package br.com.comment.work.config;

import br.com.comment.work.service.CarCommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static feign.Logger.Level.FULL;

@Getter
@RequiredArgsConstructor
public class DependencyManager {

//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final OkHttpClient httpClient = new OkHttpClient();
//    private final JacksonEncoder jacksonEncoder = new JacksonEncoder();
//    private final JacksonDecoder jacksonDecoder = new JacksonDecoder();
//    private final HttpErrorDecoder errorDecoder = new HttpErrorDecoder(objectMapper);
//
//    public DependencyManager() {
//        var carCommentService = carCommentService();
//
//    }
//
//    public CarCommentService carCommentService() {
//        return Feign.builder()
//                .client(httpClient)
//                .encoder(jacksonEncoder)
//                .decoder(jacksonDecoder)
//                .errorDecoder(errorDecoder)
//                .logger(new Slf4jLogger())
//                .logLevel(FULL)
//                .target(CarCommentService.class, "http://localhost:8081/api/v1/car-comments");
//    }
}