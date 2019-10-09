package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    int count();

    Long insert(String name, Long authorId, Long genreId);

    Book getById(Long id);

    List<Book> getAll();

    void deleteById(Long id);


    List<Book> getBooksByAuthorId(Long authorid);

    List<Book> getBooksByGenreId(Long genreid) ;

    List<Book> getBooksByGenre(String genreName);
}
