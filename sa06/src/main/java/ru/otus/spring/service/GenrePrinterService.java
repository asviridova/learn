package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

public interface GenrePrinterService {
    String printGenreToString(Genre genre);
}
