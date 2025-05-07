package br.com.producer.comment_producer.repositories;

import br.com.producer.comment_producer.entities.CarComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCommentRepository extends MongoRepository<CarComment, String> {
}
