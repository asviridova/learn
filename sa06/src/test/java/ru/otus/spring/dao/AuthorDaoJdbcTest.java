package ru.otus.spring.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.domain.Author;

@RunWith(SpringRunner.class) //Junit4
@JdbcTest
@Import(AuthorDaoJdbc.class) 
//@SpringBootTest(classes=AuthorDaoJdbcTest.class)
public class AuthorDaoJdbcTest {
	@Autowired
	private AuthorDaoJdbc authorDaoJdbc;

	
	@DisplayName("поиск количества авторов") 
	@Test
	public void returnAuthorCountAll() {
		assertEquals(authorDaoJdbc.count(), 3, "");
	}
	
	@DisplayName("поиск автора по книге") 
	@Test
	public void returnAuthorByBookId() {
		assertEquals(authorDaoJdbc.getAuthorByBookId(1L).getName(), "I.Goethe");
	}

	@DisplayName("создание автора") 
	@Test
	public void insertAuthor() {
		assertEquals(authorDaoJdbc.count(), 3, "");
		Author author = new Author(10L, "A.Pushkin", "Russian");
		authorDaoJdbc.insert(author);
		assertEquals(authorDaoJdbc.count(), 4, "");
	}
	
}
