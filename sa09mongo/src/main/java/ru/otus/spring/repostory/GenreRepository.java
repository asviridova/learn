package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
