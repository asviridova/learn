package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, Long> {
//public interface GenreRepository extends CrudRepository<Genre, String> {

    List<Genre> findAll();
}
