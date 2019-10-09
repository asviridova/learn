package ru.otus.spring.shell;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.*;

//https://projects.spring.io/spring-shell/

@ShellComponent
public class LibraryCommands {

	private final GenreService genreService;
	private final BookService bookService;
	private final AuthorService authorService;

	private final AuthorPrinterService authorPrinterService;
    private final BookPrinterService bookPrinterService;
    private final GenrePrinterService genrePrinterService;


	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	public LibraryCommands(GenreService genreService, BookService bookService, AuthorService authorService, AuthorPrinterService authorPrinterService,
                           BookPrinterService bookPrinterService, GenrePrinterService genrePrinterService) {
		this.genreService = genreService;
		this.authorService = authorService;
		this.bookService = bookService;
		this.authorPrinterService = authorPrinterService;
        this.bookPrinterService = bookPrinterService;
        this.genrePrinterService = genrePrinterService;
	}


	@ShellMethod(value = "getBooksByGenre",  key ={ "books-genre", "bg" })
	public String getBooksByGenre(String genreName){
        return bookService.getBooksByGenre(genreName).stream().map(bookPrinterService::printBookToString).collect(Collectors.joining("\n"));
	}

	
	@ShellMethod(value = "getBooksById",  key ={ "book-id", "bid" })
	public String getBooksById(Long bookid){
		return bookPrinterService.printBookToString(bookService.getById(bookid));
		
	}

	@ShellMethod(value = "getGenreByBookId",  key ={ "genre-bookid", "gbid" })
	public String getGenreByBookId(Long bookid){
		return genrePrinterService.printGenreToString(genreService.getGenreByBookId(bookid));
		
	}
	
	@ShellMethod(value = "getAllAuthors",  key ={ "authors", "a" })
	public String getAllAuthors(){
        return authorService.getAll().stream().map(authorPrinterService::printAuthorToString).collect(Collectors.joining("\n"));

	}
	@ShellMethod(value = "getAllGenres",  key ={ "genres", "g" })
	public String getAllGenres(){
		return genreService.getAll().stream().map(genrePrinterService::printGenreToString).collect(Collectors.joining("\n"));
	}

	@ShellMethod(value = "addBook",  key ={ "addbook", "ab" })
	public Long addBook(String name, Long authorId, Long genreId){
		return bookService.insert(name, authorId, genreId);
	}
	
}
