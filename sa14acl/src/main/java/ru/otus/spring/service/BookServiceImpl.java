package ru.otus.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookDaoCustom;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookDaoCustom bookDaoCustom;

    private final GenreService genreService;
    private final AuthorService authorService;

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreService genreService, AuthorService authorService,  BookDaoCustom bookDaoCustom){
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookDaoCustom = bookDaoCustom;
    }

    @Override
    public long count() {
        return bookDao.count();
    }

    @Override
    public Long insert(String name, Long authorId, Long genreId) {
        Optional<Author> author = authorService.getById(authorId);
        Optional<Genre> genre = genreService.getById(genreId);
        if(author!=null && genre.get() != null) {
            Book book = new Book(name, author.get(), genre.get());
            Book bookNew = bookDao.save(book );
            LOGGER.info("Book inserted with id = "+bookNew.getId()+", name = "+name+", authorname = "+author.get().getName()+" genrename="+genre.get().getName());
            return bookNew.getId();
        }
        return null;
    }

    @Override
    public Long update(Long id, String name, Long authorId, Long genreId) {
        Optional<Author> author = authorService.getById(authorId);
        Optional<Genre> genre = genreService.getById(genreId);
        if(author!=null && genre.get() != null) {
            Book book = new Book(id, name, author.get(), genre.get());
            //Book bookNew = bookDao.save(book );
            Book bookNew = updateBookInRepository(book);
            LOGGER.info("Book inserted with id = "+bookNew.getId()+", name = "+name+", authorname = "+author.get().getName()+" genrename="+genre.get().getName());
            return bookNew.getId();
        }
        return null;
    }

    //@PreAuthorize("hasPermission(#book, 'WRITE')")
    private Book updateBookInRepository(Book book){
        Book bookNew = bookDaoCustom.save(book );
        return bookNew;
    }


    @Override
    public Optional<Book> getById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public Iterable<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorid) {
        return bookDao.findBooksByAuthorId(authorid);
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreid) {
        return bookDao.findBooksByGenreId(genreid);
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        return bookDao.findBooksByGenreName(genreName);
    }
}
