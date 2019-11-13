package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AuthorDaoJpaTest {
	@Autowired
	private AuthorDao authorDao;

    public static int EXPECTED_AUTHOR_ALL = 3;
    public static int EXPECTED_AUTHOR_AFTER_INSERT = 4;


	@DisplayName("поиск количества авторов") 
	@Test
	public void returnAuthorCountAll() {
		assertEquals(authorDao.count(), EXPECTED_AUTHOR_ALL, "");
	}
	
	@DisplayName("поиск автора по книге") 
	@Test
	public void returnAuthorByBookId() {
	    Author author = authorDao.getAuthorByBookId(1L);
		assertEquals(author.getName(), "I.Goethe");
	}

	@DisplayName("создание автора") 
	@Test
	public void insertAuthor() {
		assertEquals(authorDao.count(), EXPECTED_AUTHOR_ALL, "");
		Author author = new Author(10L, "A.Pushkin", "Russian");
		authorDao.save(author);
		assertEquals(authorDao.count(), EXPECTED_AUTHOR_AFTER_INSERT, "");
	}
	
}
