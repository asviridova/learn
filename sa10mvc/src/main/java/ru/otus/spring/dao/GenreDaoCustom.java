package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

public interface GenreDaoCustom {

    Genre getGenreByBookId(Long id);
}
