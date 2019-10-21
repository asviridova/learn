package ru.otus.spring.dao;

import ru.otus.spring.domain.CommentBook;

import java.util.List;

public interface CommentDaoCustom {
    List<CommentBook> getAllByBookId(Long bookId);
}
