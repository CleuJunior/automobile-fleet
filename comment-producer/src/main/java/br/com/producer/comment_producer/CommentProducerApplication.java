package br.com.producer.comment_producer;

import br.com.producer.comment_producer.entities.CarComment;
import br.com.producer.comment_producer.repositories.CarCommentRepository;
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

//    @Autowired
//    CarCommentRepository repository;
//
//    @Bean CommandLineRunner runner() {
//        return args -> {
//            // Insert some sample data
//            var comment = new CarComment();
//            comment.setName("Jane Smith");
//            comment.setCarId("df2a0e36-0c8d-49bc-bd7f-2a7f54de8c9d");
//            comment.setComment("Great car!");
//            comment.setRating(9);
////            repository.save(new CarComment("John Doe", "df2a0e36-0c8d-49bc-bd7f-2a7f54de8c9d", "Comment", 10));
//            repository.save(comment);
//        };
//    }

}
