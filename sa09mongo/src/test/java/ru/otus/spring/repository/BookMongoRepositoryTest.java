package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
//@ComponentScan("ru.otus.spring")
@EnableMongoRepositories(basePackages = "ru.otus.spring.repository")
public class BookMongoRepositoryTest {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private GenreRepository genreRepository;


    public static int EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE = 2;
    public static int EXPECTED_BOOKS_BEFORE_DELETE = 4;
    public static int EXPECTED_BOOKS_AFTER_DELETE = 3;

	public static String Shakespeare_ID = "2";

	@Test
	public void createBook() {
		bookRepository.save(new Book("BookMy", new Author("A1", "N1"), new Genre("G1")));
		assertEquals(bookRepository.count(), 1, "");
	}


	@DisplayName("поиск количества книг по  жанру") 
	@Test
	public void returnBookCountByGenreID() {
		assertEquals(((Collection<Book>) bookRepository.findAllByGenreName("tragedy")).size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}
	
	@DisplayName("поиск количества книг") 
	@Test
	public void returnBookCountAll() {
		assertEquals(bookRepository.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookDeleteByID() {
		assertEquals(bookRepository.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
		bookRepository.deleteById("1");
		assertEquals(bookRepository.count(), EXPECTED_BOOKS_AFTER_DELETE, "");
	}
	
	@Test
	public void bookAll() {
		Iterable<Book> books = bookRepository.findAll();
		assertEquals(((Collection<Book>) books).size(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookByGenre() {
	    List<Book> books = bookRepository.findAllByGenreName("tragedy");
		assertEquals(books.size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}

	@Test
	public void bookByID() {
		assertEquals(bookRepository.findById(Shakespeare_ID).get().getAuthor().getName(), "W.Shakespeare");
	}
	
}
