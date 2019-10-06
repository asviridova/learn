package ru.otus.spring;

import java.util.List;
import java.util.Optional;

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
}
