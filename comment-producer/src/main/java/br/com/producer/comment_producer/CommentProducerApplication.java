package br.com.producer.comment_producer;

import br.com.producer.comment_producer.dto.request.CarCommentRequest;
import br.com.producer.comment_producer.services.CarCommentService;
import br.com.producer.comment_producer.services.SerializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommentProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentProducerApplication.class, args);
    }

    @Autowired
    CarCommentService carCommentService;
    @Autowired
    SerializationService serializationService;


    @Bean
    CommandLineRunner run() {
        return args -> {
            String jsonMessage = serializationService.toJson(new CarCommentRequest("Foel", "df2a0e36-0c8d-49bc-bd7f-2a7f54de8c9d", "Comment SQS", 4));


            carCommentService.sendComment(jsonMessage);

        };
    }

}
