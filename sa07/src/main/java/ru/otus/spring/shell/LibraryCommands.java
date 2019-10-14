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

    //Получение по идентификатору

	@ShellMethod(value = "getBooksById",  key ={ "book-id", "bid" })
	public String getBooksById(Long bookid){
		return bookPrinterService.printBookToString(bookService.getById(bookid));
		
	}

    @ShellMethod(value = "getAuthorById",  key ={ "author-id", "aid" })
    public String getAuthorById(Long authorid){
        return authorPrinterService.printAuthorToString(authorService.getById(authorid));

    }

    @ShellMethod(value = "getGenreById",  key ={ "genre-id", "gid" })
    public String getGenreById(Long genreid){
        return genrePrinterService.printGenreToString(genreService.getById(genreid));

    }
    //Получение всех сущностей

    @ShellMethod(value = "getAllAuthors",  key ={ "authors", "a" })
    public String getAllAuthors(){
        return authorPrinterService.printAuthorListToString(authorService.getAll());

    }
    @ShellMethod(value = "getAllGenres",  key ={ "genres", "g" })
    public String getAllGenres(){
        return genrePrinterService.printGenreListToString(genreService.getAll());
    }

    @ShellMethod(value = "getAllBooks",  key ={ "books", "b" })
    public String getAllBooks(){
        return bookPrinterService.printBookListToString(bookService.getAll());

    }

    //дополнительные методы рабочие
    @ShellMethod(value = "getAuthorByBookId",  key ={ "authorbybook", "abb" })
    public String getAuthorByBookId(Long bookId){
        return authorPrinterService.printAuthorToString(authorService.getAuthorByBookId(bookId));

    }
    @ShellMethod(value = "getGenreByBookId",  key ={ "genre-bookid", "gbid" })
    public String getGenreByBookId(Long bookid){
        return genrePrinterService.printGenreToString(genreService.getGenreByBookId(bookid));

    }

    @ShellMethod(value = "getBooksByAuthorId",  key ={ "books-author", "baid" })
    public String getBooksByAuthorId(Long authorid){
        return bookPrinterService.printBookListToString(bookService.getBooksByAuthorId(authorid));
    }

    @ShellMethod(value = "getBooksByGenreId",  key ={ "books-genre-id", "bgid" })
    public String getBooksByGenreId(Long genreId){
        return bookPrinterService.printBookListToString(bookService.getBooksByGenreId(genreId));
    }

    @ShellMethod(value = "getBooksByGenre",  key ={ "books-genre", "bg" })
    public String getBooksByGenre(String genreName){
        return bookPrinterService.printBookListToString(bookService.getBooksByGenre(genreName));
    }

    //дополнительные методы

	@ShellMethod(value = "addBook",  key ={ "addbook", "ab" })
	public Long addBook(String name, Long authorId, Long genreId){
		return bookService.insert(name, authorId, genreId);
	}


    @ShellMethod(value = "createAndDeleteAuthor",  key ={ "createdeleteauthor", "cda" })
    public void createAndDeleteAuthor(){
        LOGGER.info(authorService.count());

        Author author = new Author();
        author.setId(10L);
        author.setName("M.Rid");
        author.setNationality("American");
        Long id = authorService.insert(author);
        authorPrinterService.printAuthorToString(author);
        LOGGER.info("AuthorToString"+author.getName());
        LOGGER.info(authorService.count());

        authorService.deleteById(author.getId());
        LOGGER.info(authorService.count());
    }


}
