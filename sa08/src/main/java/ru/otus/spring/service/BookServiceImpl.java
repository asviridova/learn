package ru.otus.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreService genreService, AuthorService authorService){
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public Long insert(String name, Long authorId, Long genreId) {
        Author author = authorService.getById(authorId);
        Optional<Genre> genre = genreService.getById(genreId);
        if(author!=null && genre.get() != null) {
            Book book = new Book(name, author, genre.get());
            Long id = bookDao.insert(book );
            LOGGER.info("Book inserted with id = "+id+", name = "+name+", authorname = "+author.getName()+" genrename="+genre.get().getName());
            return id;
        }
        return null;
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
