package ru.otus.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;

//https://projects.spring.io/spring-shell/

//@ShellComponenent
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
	
	//@ShellMethod("books")
	public List<Book> getBooksByGenre(String genreName){
		return bookDao.getBooksByGenre(genreName);
		
	}
	
}
