package com.automobilefleet;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.automobilefleet.api.dto.request.CarCommentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@Slf4j
public class AutomobileFleetApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomobileFleetApplication.class, args);
    }

//    @Autowired
//    private AmazonSNS amazonSNS;
//    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:000000000000:mytopic";
//
//
//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            // Criar o objeto com os dados
//            CarCommentRequest feedback = new CarCommentRequest(
//                    "Frajola Piu-Piu",
//                    UUID.fromString("82bd1d96-85ac-4559-9ce6-378ef489d078"),
//                    "Muito nefasto",
//                    99
//            );
//
//            // Converter para JSON
//            String jsonMessage = objectMapper.writeValueAsString(feedback);
//
//            // Configurar e enviar a mensagem
//            PublishRequest request = new PublishRequest()
//                    .withTopicArn(TOPIC_ARN)
//                    .withMessage(jsonMessage)
//                    .withSubject("Feedback do Carro");
//
////            String json = objectMapper.writeValueAsString(request.getMessage());
//
////            amazonSNS.publish(request);
//            log.info("JSON enviado: {}", jsonMessage);
//        };
//    }


}
