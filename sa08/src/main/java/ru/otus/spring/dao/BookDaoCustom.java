package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDaoCustom {

    List<Book> getBooksByAuthorId(Long authorid);

    List<Book> getBooksByGenreId(Long genreid) ;

    List<Book> getBooksByGenre(String genreName);

}
