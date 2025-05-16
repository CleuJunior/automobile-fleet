package com.automobilefleet.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.automobilefleet.api.dto.response.RentalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnsPublisherService {

    private final AmazonSNS amazonSNS;
    private final ObjectMapper objectMapper;

    public void publishRental(RentalResponse rentalResponse, String topicArn) {
        try {
            var json = objectMapper.writeValueAsString(rentalResponse);

            var request = new PublishRequest()
                    .withTopicArn(topicArn)
                    .withMessage(json);

            PublishResult result = amazonSNS.publish(request);
            System.out.println("Mensagem enviada com ID: " + result.getMessageId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar evento SNS", e);
        }
    }

}
