package ru.otus.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;

//https://projects.spring.io/spring-shell/

//@ShellComponenent
public class LibraryCommands {

	private final GenreDao genreDao;
	
	@Autowired
	public LibraryCommands(GenreDao genreDao) {
		this.genreDao = genreDao;
	}
	
	//@ShellMethod("books")
	public List<Book> getBooksByGenre(String genreName){
		return genreDao.getBooksByGenre(genreName);
		
	}
	
}
