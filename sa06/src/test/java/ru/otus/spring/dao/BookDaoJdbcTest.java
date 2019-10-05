package ru.otus.spring.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import ru.otus.spring.dao.BookDaoJdbc;

@RunWith(SpringRunner.class) //Junit4
@JdbcTest
@Import(BookDaoJdbc.class) 
//@SpringBootTest(classes=BookDaoJdbcTest.class)

public class BookDaoJdbcTest {
	@Autowired
	private BookDaoJdbc bookDaoJdbc;

	public static int TWO = 2;
	
	@DisplayName("поиск количества книг по  жанру") 
	@Test
	public void returnBookCountByGenreID() {
		assertEquals(bookDaoJdbc.getBooksByGenre("tragedy").size(), TWO, "");
	}
	
	@DisplayName("поиск количества книг") 
	@Test
	public void returnBookCountAll() {
		assertEquals(bookDaoJdbc.count(), 4, "");
	}

	@Test
	public void bookDeleteByID() {
		assertEquals(bookDaoJdbc.count(), 4, "");
		bookDaoJdbc.deleteById(1L);
		assertEquals(bookDaoJdbc.count(), 3, "");
	}
	
	@Test
	public void bookAll() {
		assertEquals(bookDaoJdbc.getAll().size(), 4, "");
	}

	@Test
	public void bookByGenre() {
		assertEquals(bookDaoJdbc.getBooksByGenre("tragedy").size(), 2, "");
	}

	@Test
	public void bookByID() {
		assertEquals(bookDaoJdbc.getById(2L).getAuthor().getName(), "W.Shakespeare");
	}
	
}
