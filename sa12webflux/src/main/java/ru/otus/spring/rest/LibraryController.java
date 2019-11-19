package ru.otus.spring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.page.NotFoundException;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public LibraryController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/api/books")
    public Flux<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/api/authors")
    public Flux<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/api/genres")
    public Flux<Genre> getAllGenres() {
        Flux<Genre> list = genreService.getAll();
        log.debug("Genres:"+list);
        return list;
    }

    @GetMapping("/api/book")
    public Mono<Book> findBook(@RequestParam("id") String id) {
        Mono<Book> book = bookService.getById(id);
        log.debug("book:"+book);
        return book;
    }

    @GetMapping("/api/remove")
    public void removeBook(@RequestParam("id") String id, Model model) {
        bookService.deleteById(id);
    }

    @PostMapping("/api/save")
    public void saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId,
                         @RequestParam("genreId") String genreId) {
        log.debug("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.update(id, name, authorId, genreId);
    }

    @PostMapping("/api/insert")
    public void insertBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId,
                           @RequestParam("genreId") String genreId) {
        log.debug("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.insert(name, authorId, genreId);
    }

    @GetMapping("/api/edit")
    public void editBook(@RequestParam("id") long id, Model model) {
    }


}
