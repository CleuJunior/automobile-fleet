package br.com.comment.work.service;

import br.com.comment.work.domain.CarCommentRequest;
import br.com.comment.work.error.BadRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Converter {

    private final ObjectMapper deserializer = new ObjectMapper();

    public CarCommentRequest carCommentRequest(String body) {
        try {
            return deserializer.readValue(body, CarCommentRequest.class);
        } catch (IOException cause) {
            throw new BadRequestException("Invalid event format: %s", cause.getMessage());
        }
    }

    public <T> T deserialize(String body, TypeReference<T> type) {
        try {
            return deserializer.readValue(body, type);
        } catch (IOException cause) {
            throw new BadRequestException("Invalid event format: %s", cause.getMessage());
        }
    }

}
