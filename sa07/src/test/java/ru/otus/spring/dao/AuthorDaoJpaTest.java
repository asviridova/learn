package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorPrinterServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({AuthorDaoJPA.class, AuthorPrinterServiceImpl.class})
public class AuthorDaoJpaTest {
	@Autowired
	private AuthorDaoJPA authorDaoJpa;

    public static int EXPECTED_AUTHOR_ALL = 3;
    public static int EXPECTED_AUTHOR_AFTER_INSERT = 4;

    @Autowired
    private AuthorPrinterServiceImpl authorPrinterService;

	@DisplayName("поиск количества авторов") 
	@Test
	public void returnAuthorCountAll() {
		assertEquals(authorDaoJpa.count(), EXPECTED_AUTHOR_ALL, "");
	}
	
	@DisplayName("поиск автора по книге") 
	@Test
	public void returnAuthorByBookId() {
	    Author author = authorDaoJpa.getAuthorByBookId(1L);
        System.out.println(authorPrinterService.printAuthorToString(author));
		assertEquals(author.getName(), "I.Goethe");
	}

	@DisplayName("создание автора") 
	@Test
	public void insertAuthor() {
		assertEquals(authorDaoJpa.count(), EXPECTED_AUTHOR_ALL, "");
		Author author = new Author(10L, "A.Pushkin", "Russian");
		authorDaoJpa.insert(author);
		assertEquals(authorDaoJpa.count(), EXPECTED_AUTHOR_AFTER_INSERT, "");
	}
	
}
