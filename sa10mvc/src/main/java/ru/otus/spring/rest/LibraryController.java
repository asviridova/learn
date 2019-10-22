package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import java.util.Collection;
import java.util.List;

//https://html.com/tags/
//https://www.thymeleaf.org/doc/articles/petclinic.html

//http://localhost:8080/

@Controller
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @Autowired
    public LibraryController(BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        Iterable<Book> books = bookService.getAll();
        model.addAttribute("books",  ((Collection<Book>) books));
        Iterable<Author> authors = authorService.getAll();
        model.addAttribute("authors",  ((Collection<Author>) authors));
        Iterable<Genre> genres = genreService.getAll();
        model.addAttribute("genres",  ((Collection<Genre>) genres));

        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(new Long(id)).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/save")
    public String savePage(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("authorId") Long authorId,
                           @RequestParam("genreId") Long genreId, Model model) {
        Long idNew = bookService.update(id, name, authorId, 2L); //TODO comedy
        //-----
        Iterable<Book> books = bookService.getAll();
        model.addAttribute("books",  ((Collection<Book>) books));
        return "list";
    }

    @PostMapping("/edit")
    public String savePageWithPost(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("authorId") Long authorId,
                           @RequestParam("genreId") Long genreId, Model model) {
        Long idNew = bookService.update(id, name, authorId, 2L); //TODO comedy
        //-----
        Iterable<Book> books = bookService.getAll();
        model.addAttribute("books",  ((Collection<Book>) books));
        return "list";
    }

}



