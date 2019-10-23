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

//https://habr.com/ru/post/435062/
//https://o7planning.org/ru/11659/thymeleaf-form-select-option-example

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
        fillStartPageModel (model);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(new Long(id)).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        fillAuthorAndGenreModel(model);
        return "edit";
    }

    @GetMapping("/remove")
    public String removeBook(@RequestParam("id") long id, Model model) {
        bookService.deleteById(id);
        fillStartPageModel(model);
        return "list";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        fillAuthorAndGenreModel(model);
        return "create";
    }

    @PostMapping("/save")
    public String savePage(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("authorId") Long authorId,
                           @RequestParam("genreId") Long genreId, Model model) {
        System.out.println("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        Long idNew = bookService.update(id, name, authorId, genreId);
        fillStartPageModel (model);
        return "list";
    }

    @PostMapping("/insert")
    public String insertPage(@RequestParam("name") String name, @RequestParam("authorId") Long authorId,
                           @RequestParam("genreId") Long genreId, Model model) {
        System.out.println("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        Long idNew = bookService.insert(name, authorId, genreId);
        fillStartPageModel (model);
        return "list";
    }

    private void fillStartPageModel(Model model){
        Iterable<Book> books = bookService.getAll();
        model.addAttribute("books",  ((Collection<Book>) books));
        fillAuthorAndGenreModel(model);
    }

    private void fillAuthorAndGenreModel(Model model){
        Iterable<Author> authors = authorService.getAll();
        model.addAttribute("authors",  ((Collection<Author>) authors));
        Iterable<Genre> genres = genreService.getAll();
        model.addAttribute("genres",  ((Collection<Genre>) genres));
    }

}



