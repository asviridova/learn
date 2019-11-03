package ru.otus.spring.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void insert(String name, String authorId, String genreId);

    void update(String id, String name, String authorId, String genreId);

    Mono<Book> getById(String id);

    Flux<Book> getAll();

    void deleteById(String id);

    Flux<Book> getBooksByAuthorId(String authorid);

    Flux<Book> getBooksByGenreId(String genreid) ;

    Flux<Book> getBooksByGenre(String genreName);
}
