package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookPrinterServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest

public class BookDaoJpaTest {
	@Autowired
	private BookDao bookDao;

    public static int EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE = 2;
    public static int EXPECTED_BOOKS_BEFORE_DELETE = 4;
    public static int EXPECTED_BOOKS_AFTER_DELETE = 3;

	public static long Shakespeare_ID = 2L;

	@DisplayName("поиск количества книг по  жанру") 
	@Test
	public void returnBookCountByGenreID() {
		assertEquals(((Collection<Book>)bookDao.findBooksByGenreName("tragedy")).size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}
	
	@DisplayName("поиск количества книг") 
	@Test
	public void returnBookCountAll() {
		assertEquals(bookDao.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookDeleteByID() {
		assertEquals(bookDao.count(), EXPECTED_BOOKS_BEFORE_DELETE, "");
		bookDao.deleteById(1L);
		assertEquals(bookDao.count(), EXPECTED_BOOKS_AFTER_DELETE, "");
	}
	
	@Test
	public void bookAll() {
		Iterable<Book> books = bookDao.findAll();
		assertEquals(((Collection<Book>) books).size(), EXPECTED_BOOKS_BEFORE_DELETE, "");
	}

	@Test
	public void bookByGenre() {
	    List<Book> books = bookDao.findBooksByGenreName("tragedy");
		assertEquals(books.size(), EXPECTED_BOOKS_COUNT_WITH_TRAGEDY_GENRE, "");
	}

	@Test
	public void bookByID() {
		assertEquals(bookDao.findById(Shakespeare_ID).get().getAuthor().getName(), "W.Shakespeare");
	}
	
}
