package ru.otus.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface GenreDao extends CrudRepository<Genre, Long>, GenreDaoCustom {

}


