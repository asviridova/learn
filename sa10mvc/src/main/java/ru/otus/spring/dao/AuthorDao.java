package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Author;

public interface AuthorDao extends CrudRepository<Author, Long> {

    @Query("select distinct a from Author a where a.id in (select b.author.id from Book b where b.id = :id)")
    Author getAuthorByBookId(@Param("id") Long id);

}
