package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

public interface AuthorDaoCustom {

    Author getAuthorByBookId(Long id);

}
