package ru.otus.spring.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface BookDao extends JpaRepository<Book, Long>  {

    List<Book> findBooksByGenreName(String genreName);

    List<Book> findBooksByAuthorId(Long authorId);

    List<Book> findBooksByGenreId(Long genreId) ;

    @EntityGraph(value = "BookGraph")
    List<Book> findAll();

    //@PreAuthorize("hasPermission(#book, 'WRITE')")
    //Book save(@Param("book") Book book);

}
