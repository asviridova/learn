package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

//public interface AuthorRepository extends CrudRepository<Author, String> {
public interface AuthorRepository extends MongoRepository<Author, Long> {

    List<Author> findAll();
}
