package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

public interface AuthorDao {
	int count();

    Long insert(Author author);

    Author getById(Long id);

    List<Author> getAll();

    void deleteById(Long id);
    
    Author getAuthorByBookId(Long id);

}
