package ru.otus.spring.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface BookDao {
	int count();

    void insert(Book book);

    Book getById(Long id);

    List<Book> getAll();

    void deleteById(Long id);
    
    Genre getGenreByBookId(Long id);
    
    Author getAuthorByBookId(Long id);
    
	List<Book> getBooksByAuthorId(Long authorid);

	List<Book> getBooksByGenreId(Long genreid) ;

	List<Book> getBooksByGenre(String genreName);
	
	
}
