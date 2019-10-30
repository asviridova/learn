package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
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
//@EnableMongoRepositories(basePackages = "ru.otus.spring.repository")
//@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.config", "ru.otus.spring.repository"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class BookMongoRepositoryTest {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private MongoOperations authorRepository;
	@Autowired
	private MongoOperations genreRepository;


    public static int EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE = 2;
    public static int EXPECTED_BOOKS_BEFORE_DELETE = 4;
    public static int EXPECTED_BOOKS_AFTER_DELETE = 3;
	public static int EXPECTED_BOOKS_AFTER_ADD = 5;

	public static String Shakespeare_ID = "2";

	@Test
	public void createBook() {
		Author a = new Author("A1", "N1");
		Author aNew = authorRepository.save(a);
		Genre g = new Genre("G1");
		Genre gNew = genreRepository.save(g);

		Book bookNew = bookRepository.save(new Book("BookMy", aNew, gNew));
		assertEquals(bookRepository.count(), EXPECTED_BOOKS_AFTER_ADD, "");

		bookRepository.deleteById(bookNew.getId());
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
	@DisplayName("поиск количества книг по  жанру")
	public void bookByID() {
		assertEquals(bookRepository.findById(Shakespeare_ID).get().getAuthor().getName(), "W.Shakespeare");
	}
	
}
