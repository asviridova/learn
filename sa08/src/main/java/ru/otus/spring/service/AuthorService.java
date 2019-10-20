package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long count();

    Long insert(Author author);

    Long insert(String name, String nationality);

    Optional<Author> getById(Long id);

    Iterable<Author> getAll();

    void deleteById(Long id);

    Author getAuthorByBookId(Long id);
}
