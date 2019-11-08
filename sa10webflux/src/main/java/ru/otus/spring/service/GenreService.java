package ru.otus.spring.service;

import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;

public interface GenreService {

    Flux<Genre> getAll();
}
