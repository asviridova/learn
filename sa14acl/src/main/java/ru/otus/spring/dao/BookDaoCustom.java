package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDaoCustom extends JpaRepository<Book, Long> {

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    Book save(@Param("book") Book book);

}
