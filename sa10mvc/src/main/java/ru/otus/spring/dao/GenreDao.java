package ru.otus.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface GenreDao extends CrudRepository<Genre, Long>/*, GenreDaoCustom */{

    @Query("select distinct g from Genre g where g.id in (select b.genre.id from Book b where b.id = :id)")
    Genre getGenreByBookId(@Param("id") Long id);
}


