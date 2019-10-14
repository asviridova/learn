package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    int count();

    Long insert(Genre genre);

    Genre getById(Long id);

    List<Genre> getAll();

    void deleteById(Long id);

    Genre getGenreByBookId(Long id);
}
