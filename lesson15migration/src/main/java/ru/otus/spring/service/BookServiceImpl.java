package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository ){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public String insert(String name, String authorId, String genreId) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Genre> genre = genreRepository.findById(genreId);
        if(author.isPresent() && genre.isPresent()) {
            Book book = new Book(name, author.get(), genre.get());

            Book bookNew = bookRepository.save(book );
            log.info("Book inserted with id = "+bookNew.getId()+", name = "+name+", authorname = "+author.get().getName()+" genrename="+genre.get().getName());
            return bookNew.getId();
        }
        return null;
    }

    @Override
    public String update(String id, String name, String authorId, String genreId) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Genre> genre = genreRepository.findById(genreId);
        if(author.isPresent() && genre.isPresent()) {
            Book book = new Book(id, name, author.get(), genre.get());
            Book bookNew = bookRepository.save(book );
            log.info("Book updated with id = "+bookNew.getId()+", name = "+name+", authorname = "+author.get().getName()+" genrename="+genre.get().getName());
            return bookNew.getId();
        }
        return null;
    }


    @Override
    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(String authorid) {
        return bookRepository.findAllByAuthorId(authorid);
    }

    @Override
    public List<Book> getBooksByGenreId(String genreid) {
        return bookRepository.findAllByGenreId(genreid);
    }

    @Override
    public List<Book> getBooksByGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        if(genre!=null) {
            return bookRepository.findAllByGenreId(genre.getId());
        }
        return null;
    }
}
