package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.time.Duration;

//https://www.mkyong.com/spring-boot/spring-boot-webflux-thymeleaf-reactive-example/

@Controller
public class LibraryReactorController {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    private BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;



    @Autowired
    public LibraryReactorController(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;    }

    @GetMapping("/")
    public String listPage(Model model) {
        fillStartPageModel (model);
        return "list";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") String id, Model model) {
        IReactiveDataDriverContextVariable book = new ReactiveDataDriverContextVariable(bookService.getById(id), 1);
        model.addAttribute("book", book);
        fillAuthorAndGenreModel(model);
        return "edit";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam("id") String id, Model model) {
        bookService.deleteById(id);
        return "redirect:/";
    }


    @PostMapping("/add")
    public String addBookPage(Model model) {
        fillAuthorAndGenreModel(model);
        return "create";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId,
                           @RequestParam("genreId") String genreId, Model model) {
        System.out.println("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.update(id, name, authorId, genreId);
        return "redirect:/";
    }

    @PostMapping("/insert")
    public String insertBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId,
                             @RequestParam("genreId") String genreId, Model model) {
        System.out.println("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.insert(name, authorId, genreId);
        return "redirect:/";
    }


    //---Rest methods
/*    @GetMapping("/books")
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
    public Mono<Book> saveBook(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("authorId") String authorId,
                           @RequestParam("genreId") String genreId) {
        System.out.println("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        return bookService.update(id, name, authorId, genreId);
    }

    @PostMapping("/bookinsertwithparams")
    public Mono<Book> insertBook(@RequestParam("name") String name, @RequestParam("authorId") String authorId,
                             @RequestParam("genreId") String genreId) {
        System.out.println("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        return bookService.insert(name, authorId, genreId);
    }

    @GetMapping("/deleteauthor/{authorid}")
    public String deleteAuthor(@PathVariable("authorid") String authorId) {
        System.out.println("FROM FORM: authorId="+authorId);
        authorRepository.deleteById(authorId);
        return "Author Deleted";
    }

    @GetMapping("/deletebook/{bookid}")
    public String deleteBook(@PathVariable("bookid") String bookId) {
        System.out.println("FROM FORM: bookId="+bookId);
        bookRepository.deleteById(bookId);
        return "Book Deleted";
    }
*/
    //------------
    private void fillStartPageModel(Model model){
        IReactiveDataDriverContextVariable reactiveBooks = new ReactiveDataDriverContextVariable(bookService.getAll(), 1);
        model.addAttribute("books", reactiveBooks);
        fillAuthorAndGenreModel(model);
    }

    private void fillAuthorAndGenreModel(Model model){
        IReactiveDataDriverContextVariable reactiveAuthors = new ReactiveDataDriverContextVariable(authorService.getAll(), 1);
        model.addAttribute("authors", reactiveAuthors);

        IReactiveDataDriverContextVariable reactiveGenres = new ReactiveDataDriverContextVariable(authorService.getAll(), 1);
        model.addAttribute("authors", reactiveAuthors);

        Flux<Author> authors = authorService.getAll();
        model.addAttribute("authors",  (authors));
        Flux<Genre> genres = genreService.getAll();
        model.addAttribute("genres",  (genres));
    }


}
