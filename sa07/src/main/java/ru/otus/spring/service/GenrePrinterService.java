package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenrePrinterService {
    String printGenreToString(Genre genre);

    String printGenreListToString(List<Genre> genreList);
}
