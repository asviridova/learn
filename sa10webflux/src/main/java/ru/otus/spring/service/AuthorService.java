package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

public interface AuthorService {

    void deleteById(String id);

    String insert(String name, String nationality);

    String insert(Author author);

}
