package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenrePrinterServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({GenreDaoJdbc.class, GenrePrinterServiceImpl.class})
public class GenreDaoJdbcTest {

	@Autowired
	private GenreDaoJdbc genreDaoJdbc;

	@Autowired
	private GenrePrinterServiceImpl genrePrinterService;

	public static int EXPECTED_GENRE_ALL = 4;

	public static String JENRE_TRAGEDY = "tragedy";
	
	@DisplayName("количество жанров в БД") 
	@Test
	public void returnCountGenre() {
		System.out.println("genreDaoJdbc="+genreDaoJdbc);
		assertThat(genreDaoJdbc.count()).isEqualTo(EXPECTED_GENRE_ALL);
	}

	@DisplayName("поиск жанра по ид") 
	@Test
	public void returnGenreByID() {
		Genre genre = genreDaoJdbc.getById(1L);
		System.out.println(genrePrinterService.printGenreToString(genre));

		assertThat(genre).hasFieldOrPropertyWithValue("name", JENRE_TRAGEDY);
	}

	@DisplayName("поиск жанра по ид книги") 
	@Test
	public void returnGenreByBookID() {
		assertThat(genreDaoJdbc.getGenreByBookId(1L)).hasFieldOrPropertyWithValue("name", JENRE_TRAGEDY);
	}
	
}
