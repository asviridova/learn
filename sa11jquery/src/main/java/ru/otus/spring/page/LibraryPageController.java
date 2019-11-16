package ru.otus.spring.page;

import lombok.extern.slf4j.Slf4j;
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


@Controller
@Slf4j
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

    @PostMapping("/add")
    public String addBookPage(Model model) {
        return "create";
    }

    @PostMapping("/edit")
    public String editBookPage(@RequestParam("id") long id, Model model) {
        log.debug("selected book id:"+id);
        Book book = bookService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("book_id", book.getId());
        return "edit";
    }


}



