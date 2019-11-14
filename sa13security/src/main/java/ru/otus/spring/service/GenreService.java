package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    long count();

    Long insert(Genre genre);

    Optional<Genre> getById(Long id);

    Iterable<Genre> getAll();

    void deleteById(Long id);

    Genre getGenreByBookId(Long id);
}
