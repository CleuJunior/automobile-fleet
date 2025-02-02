package br.com.producer.comment_producer.services;

import br.com.producer.comment_producer.exception.SerializationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SerializationService {

    private final ObjectMapper mapper;


    public String toJson(Object value) {
        try {
            log.info("Serialization of Object: {}", value);
            return mapper.writeValueAsString(value);

        } catch (JsonProcessingException err) {
            log.error("Could not read value as string: {}", value);
            throw new SerializationException("Could not serialize object");
        }
    }
}
