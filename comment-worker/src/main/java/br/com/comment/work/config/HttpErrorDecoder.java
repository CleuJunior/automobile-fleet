package br.com.comment.work.config;

import br.com.comment.work.domain.ErrorBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
public class HttpErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        var status = response.status();

        try {
            var stream = response.body().asInputStream();
            var body = mapper.readValue(stream, ErrorBody.class);

            return new HttpErrorStatusException(body.message, status);

        } catch (Throwable error) {
            log.error("Could not deserialize error message", error);

            var message = format("%d %s", status, status);

            return new HttpErrorStatusException(message, status);
        }
    }
}