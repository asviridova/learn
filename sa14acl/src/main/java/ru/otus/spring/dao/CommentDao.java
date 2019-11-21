package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.CommentBook;

import javax.persistence.TypedQuery;
import java.util.List;

public interface CommentDao extends CrudRepository<CommentBook, Long> /*, CommentDaoCustom*/{

    @Query("select distinct c from CommentBook c where c.book.id in (select b.id from Book b where b.id = :bookId)")
    List<CommentBook> getAllByBookId(@Param("bookId") Long bookId);



}
