package ru.otus.spring.service;

import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Author;

public interface AuthorService {

    void deleteById(String id);

    Flux<Author> getAll();

}
