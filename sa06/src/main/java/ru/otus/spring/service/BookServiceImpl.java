package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public Long insert(Book book) {
        return bookDao.insert(book);
    }

    @Override
    public Book getById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorid) {
        return bookDao.getBooksByAuthorId(authorid);
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreid) {
        return bookDao.getBooksByGenreId(genreid);
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        return bookDao.getBooksByGenre(genreName);
    }
}
