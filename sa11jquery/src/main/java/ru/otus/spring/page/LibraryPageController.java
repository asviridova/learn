package ru.otus.spring.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

//https://html.com/tags/
//https://www.thymeleaf.org/doc/articles/petclinic.html

//http://localhost:8080/

//https://habr.com/ru/post/435062/
//https://o7planning.org/ru/11659/thymeleaf-form-select-option-example

//https://stackoverflow.com/questions/17955777/redirect-to-an-external-url-from-controller-action-in-spring-mvc
//https://www.baeldung.com/spring-redirect-and-forward

@Controller
public class LibraryPageController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @Autowired
    public LibraryPageController(BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String listPageMyLib(Model model) {
        return "listbooks";
    }


    @GetMapping("/edit")
    public String editBookPage(Model model) {
        return "edit";
    }

    @PostMapping("/remove")
    public String removeBook(@RequestParam("id") long id, Model model) {
        bookService.deleteById(id);
        return "redirect:/";
    }


    @PostMapping("/add")
    public String addBookPage(Model model) {
        return "create";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("authorId") Long authorId,
                           @RequestParam("genreId") Long genreId, Model model) {
        System.out.println("FROM FORM: id="+id+", name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.update(id, name, authorId, genreId);
        return "redirect:/";
    }

    @PostMapping("/insert")
    public String insertBook(@RequestParam("name") String name, @RequestParam("authorId") Long authorId,
                           @RequestParam("genreId") Long genreId, Model model) {
        System.out.println("FROM FORM: name="+name+", authorId="+authorId+", genreId="+genreId);
        bookService.insert(name, authorId, genreId);
        return "redirect:/";
    }

}



