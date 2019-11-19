package ru.otus.spring.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.util.Collection;


@Controller
@Slf4j
public class LibraryPageController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public LibraryPageController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listPageMyLib(Model model) {
        return "listbooks";
    }


    @PostMapping("/add")
    public String addBookPage(Model model) {
        return "create";
    }


    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") String id, Model model) {
        log.debug("selected book id:"+id);
        return "edit";
    }


}



