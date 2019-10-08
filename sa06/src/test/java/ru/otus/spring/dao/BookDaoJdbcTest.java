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
@Import({BookDaoJdbc.class, BookPrinterServiceImpl.class})

public class BookDaoJdbcTest {
	@Autowired
	private BookDaoJdbc bookDaoJdbc;

	@Autowired
	private BookPrinterServiceImpl bookPrinterService;

    public static int EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE = 2;
    public static int EXPECTED_BOOKS_BEFORE_DELETE = 4;
    public static int EXPECTED_BOOKS_AFTER_DELETE = 3;

	@DisplayName("поиск количества книг по  жанру") 
	@Test
	public void returnBookCountByGenreID() {
		assertEquals(bookDaoJdbc.getBooksByGenre("tragedy").size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}
	
	@DisplayName("поиск количества книг") 
	@Test
	public void returnBookCountAll() {
		assertEquals(bookDaoJdbc.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookDeleteByID() {
		assertEquals(bookDaoJdbc.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
		bookDaoJdbc.deleteById(1L);
		assertEquals(bookDaoJdbc.count(), EXPECTED_BOOKS_AFTER_DELETE, "");
	}
	
	@Test
	public void bookAll() {
		assertEquals(bookDaoJdbc.getAll().size(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookByGenre() {
	    List<Book> books = bookDaoJdbc.getBooksByGenre("tragedy");
	    for(Book book: books) {
            System.out.println(bookPrinterService.printBookToString(book));
        }
		assertEquals(books.size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}

	@Test
	public void bookByID() {
		assertEquals(bookDaoJdbc.getById(2L).getAuthor().getName(), "W.Shakespeare");
	}
	
}
