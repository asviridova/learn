package ru.otus.spring.shell;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

//https://projects.spring.io/spring-shell/

@ShellComponent
public class LibraryCommands {

	private final GenreDao genreDao;
	private final BookDao bookDao;
	private final AuthorDao authorDao;
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	public LibraryCommands(GenreDao genreDao, BookDao bookDao, AuthorDao authorDao) {
		this.genreDao = genreDao;
		this.authorDao = authorDao;
		this.bookDao = bookDao;
	}
	
	@ShellMethod(value = "getBooksByGenre",  key ={ "books-genre", "bg" })
	public List<Book> getBooksByGenre(String genreName){
		return bookDao.getBooksByGenre(genreName);
		
	}

	
	@ShellMethod(value = "getBooksById",  key ={ "book-id", "bid" })
	public Book getBooksById(Long bookid){
		return bookDao.getById(bookid);
		
	}

	@ShellMethod(value = "getGenreByBookId",  key ={ "genre-bookid", "gbid" })
	public Genre getGenreByBookId(Long bookid){
		return genreDao.getGenreByBookId(bookid);
		
	}
	
	@ShellMethod(value = "getAllAuthors",  key ={ "authors", "a" })
	public List<Author> getAllAuthors(){
		return authorDao.getAll();
		
	}
	
	@ShellMethod(value = "getAllGenres",  key ={ "genres", "g" })
	public List<Genre> getAllGenres(){
		return genreDao.getAll();
		
	}	

	@ShellMethod(value = "addBook",  key ={ "addbook", "ab" })
	public Long addBook(String name, Long authorId, Long genreId){
		Author author = authorDao.getById(authorId);
		Genre genre = genreDao.getById(genreId);
		if(author!=null && genre != null) {
		    Book book = new Book(name, author, genre);
		    Long id = bookDao.insert(book );
		    LOGGER.info("Book inserted with id = "+id+", name = "+name+", authorname = "+author.getName()+" genrename="+genre.getName());
		    return id;
		}
		return null;
	}	
	
}
