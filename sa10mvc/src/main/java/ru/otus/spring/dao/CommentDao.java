package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.CommentBook;

import java.util.List;

public interface CommentDao extends CrudRepository<CommentBook, Long>, CommentDaoCustom{

}
