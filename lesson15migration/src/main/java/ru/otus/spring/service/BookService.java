package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    String insert(String name, String authorId, String genreId);

    String update(String id, String name, String authorId, String genreId);

    Optional<Book> getById(String id);

    List<Book> getAll();

    void deleteById(String id);

    List<Book> getBooksByAuthorId(String authorid);

    List<Book> getBooksByGenreId(String genreid) ;

    List<Book> getBooksByGenre(String genreName);
}
