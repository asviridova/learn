package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repostory.BookRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("ru.otus.spring")
@EnableMongoRepositories(basePackages = "ru.otus.spring")
public class BookMongoRepositoryTest {
	@Autowired
	private BookRepository bookRepository;

    public static int EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE = 2;
    public static int EXPECTED_BOOKS_BEFORE_DELETE = 4;
    public static int EXPECTED_BOOKS_AFTER_DELETE = 3;

	public static String Shakespeare_ID = "2";

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
