package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.service.BookService;

import java.time.Duration;

@RestController
public class LibraryReactorController {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private BookService bookService;

    @Autowired
    public LibraryReactorController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/genres")
    public Flux<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/authors")
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getBookById(@PathVariable("id") String id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/booksbyauthor/{authorid}")
    public Flux<Book> getBooksByAuthorId(@PathVariable("authorid") String authorId) {
        return bookRepository.findAllByAuthorId(authorId);
    }

    @PostMapping("/booksavewithparams")
    public String saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId,
                           @RequestParam("genreId") String genreId) {
        System.out.println("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.update(id, name, authorId, genreId);
        return "Saved!";
    }

    @PostMapping("/bookinsertwithparams")
    public String insertBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId,
                           @RequestParam("genreId") String genreId) {
        System.out.println("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.insert(name, authorId, genreId);
        return "Inserted!";
    }

    @GetMapping("/deleteauthor/{authorid}")
    public String deleteAuthor(@PathVariable("authorid") String authorId) {
        System.out.println("FROM FORM: authorId="+authorId);
        authorRepository.deleteById(authorId);
        return "Deleted Author!";
    }

    @GetMapping("/deletebook/{bookid}")
    public String deleteBook(@PathVariable("bookid") String bookId) {
        System.out.println("FROM FORM: bookId="+bookId);
        bookRepository.deleteById(bookId);
        return "Deleted Book!";
    }

}
