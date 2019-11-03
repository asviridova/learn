package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

   Flux<Book> findAllByGenreId(String genreId);

   Flux<Book> findAllByAuthorId(String authorId);

   Mono<Book> save(Mono<Book> person);
}

