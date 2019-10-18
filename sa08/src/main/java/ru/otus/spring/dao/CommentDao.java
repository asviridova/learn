package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.CommentBook;

import java.util.List;

public interface CommentDao {

    Long insert(CommentBook commentBook);
    void delete(Long id);
    List<CommentBook> getAllByBookId(Long bookId);

}
