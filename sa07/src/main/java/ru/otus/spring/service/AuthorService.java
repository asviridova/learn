package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    int count();

    Long insert(Author author);

    Long insert(String name, String nationality);

    Author getById(Long id);

    List<Author> getAll();

    void deleteById(Long id);

    Author getAuthorByBookId(Long id);
}
