package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface BookDao {
	int count();

    void insert(Book book);

    Book getById(Long id);

    List<Book> getAll();

    void deleteById(Long id);
    
    Genre getGenreById(Long id);
    
    Author getAuthorById(Long id);
}
