package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.page.NotFoundException;
import ru.otus.spring.rest.dto.AuthorDto;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.dto.GenreDto;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//https://webref.ru/dev/jqfundamentals/javascript-basics
//https://webref.ru/dev/jqfundamentals/ajax

@RestController
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

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/genres")
    public List<GenreDto> getAllGenres() {
        List<GenreDto> list = genreService.getAll().stream().map(GenreDto::toDto).collect(Collectors.toList());
        System.out.println("Genres:"+list);
        return list;
    }

    @GetMapping("/api/book")
    public BookDto findBook(@RequestParam("id") long id) {
        Book book = bookService.getById(id).orElseThrow(NotFoundException::new);
        System.out.println("book:"+book);
        return BookDto.toDto(book);
    }



}
