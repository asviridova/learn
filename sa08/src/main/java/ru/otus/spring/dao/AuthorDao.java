package ru.otus.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Author;

public interface AuthorDao extends CrudRepository<Author, Long>, AuthorDaoCustom {


}
