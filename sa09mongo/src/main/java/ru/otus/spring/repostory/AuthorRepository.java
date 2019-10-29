package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
