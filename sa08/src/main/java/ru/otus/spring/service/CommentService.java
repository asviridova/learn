package ru.otus.spring.service;

import ru.otus.spring.domain.CommentBook;

import java.util.List;

public interface CommentService {

    Long insert(Long bookId, String comment);
    void delete(Long id);
    List<CommentBook> getAll(Long bookId);
}
