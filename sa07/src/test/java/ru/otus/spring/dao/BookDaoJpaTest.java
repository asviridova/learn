package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookPrinterServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import({BookDaoJPA.class, BookPrinterServiceImpl.class})

public class BookDaoJpaTest {
	@Autowired
	private BookDaoJPA bookDaoJpa;

	@Autowired
	private BookPrinterServiceImpl bookPrinterService;

    public static int EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE = 2;
    public static int EXPECTED_BOOKS_BEFORE_DELETE = 4;
    public static int EXPECTED_BOOKS_AFTER_DELETE = 3;

	@DisplayName("поиск количества книг по  жанру") 
	@Test
	public void returnBookCountByGenreID() {
		assertEquals(bookDaoJpa.getBooksByGenre("tragedy").size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}
	
	@DisplayName("поиск количества книг") 
	@Test
	public void returnBookCountAll() {
		assertEquals(bookDaoJpa.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookDeleteByID() {
		assertEquals(bookDaoJpa.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
		bookDaoJpa.deleteById(1L);
		assertEquals(bookDaoJpa.count(), EXPECTED_BOOKS_AFTER_DELETE, "");
	}
	
	@Test
	public void bookAll() {
		assertEquals(bookDaoJpa.getAll().size(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookByGenre() {
	    List<Book> books = bookDaoJpa.getBooksByGenre("tragedy");
	    for(Book book: books) {
            System.out.println(bookPrinterService.printBookToString(book));
        }
		assertEquals(books.size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}

	@Test
	public void bookByID() {
		assertEquals(bookDaoJpa.getById(2L).getAuthor().getName(), "W.Shakespeare");
	}
	
}
