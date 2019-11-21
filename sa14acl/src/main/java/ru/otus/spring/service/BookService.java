package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long count();

    Long insert(String name, Long authorId, Long genreId);

    Long update(Long id, String name, Long authorId, Long genreId);


    Optional<Book> getById(Long id);

    Iterable<Book> getAll();

    void deleteById(Long id);


    List<Book> getBooksByAuthorId(Long authorid);

    List<Book> getBooksByGenreId(Long genreid) ;

    List<Book> getBooksByGenre(String genreName);
}
