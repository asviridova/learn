package ru.otus.spring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;

@RestController
@Slf4j
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Flux<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> findBook(@RequestParam("id") String id) {
        Mono<Book> book = bookService.getById(id);
        log.debug("book:"+book);
        return book;
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> removeBook(@RequestParam("id") String id, Model model) {
        return bookService.deleteById(id);
    }

    @PutMapping("/books/{id}")
    public Mono<Book> saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId,
                               @RequestParam("genreId") String genreId) {
        log.debug("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        return bookService.update(id, name, authorId, genreId);
    }

    @PostMapping("/books")
    public Mono<Book> insertBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId,
                                 @RequestParam("genreId") String genreId) {
        log.debug("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        return bookService.insert(name, authorId, genreId);
    }



}
