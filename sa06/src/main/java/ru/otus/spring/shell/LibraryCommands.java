package ru.otus.spring.shell;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

//https://projects.spring.io/spring-shell/

@ShellComponent
public class LibraryCommands {

	private final GenreService genreService;
	private final BookService bookService;
	private final AuthorService authorService;

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	public LibraryCommands(GenreService genreService, BookService bookService, AuthorService authorService) {
		this.genreService = genreService;
		this.authorService = authorService;
		this.bookService = bookService;
	}


	@ShellMethod(value = "getBooksByGenre",  key ={ "books-genre", "bg" })
	public List<Book> getBooksByGenre(String genreName){
		return bookService.getBooksByGenre(genreName);
		
	}

	
	@ShellMethod(value = "getBooksById",  key ={ "book-id", "bid" })
	public Book getBooksById(Long bookid){
		return bookService.getById(bookid);
		
	}

	@ShellMethod(value = "getGenreByBookId",  key ={ "genre-bookid", "gbid" })
	public Genre getGenreByBookId(Long bookid){
		return genreService.getGenreByBookId(bookid);
		
	}
	
	@ShellMethod(value = "getAllAuthors",  key ={ "authors", "a" })
	public List<Author> getAllAuthors(){
		return authorService.getAll();
		
	}
	
	@ShellMethod(value = "getAllGenres",  key ={ "genres", "g" })
	public List<Genre> getAllGenres(){
		return genreService.getAll();
		
	}	

	@ShellMethod(value = "addBook",  key ={ "addbook", "ab" })
	public Long addBook(String name, Long authorId, Long genreId){
		Author author = authorService.getById(authorId);
		Genre genre = genreService.getById(genreId);
		if(author!=null && genre != null) {
		    Book book = new Book(name, author, genre);
		    Long id = bookService.insert(book );
		    LOGGER.info("Book inserted with id = "+id+", name = "+name+", authorname = "+author.getName()+" genrename="+genre.getName());
		    return id;
		}
		return null;
	}	
	
}
