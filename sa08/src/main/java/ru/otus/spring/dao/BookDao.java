package ru.otus.spring.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface BookDao extends CrudRepository<Book, Long>, BookDaoCustom {

	
}
