package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}
