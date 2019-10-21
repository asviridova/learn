package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenrePrinterServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({GenreDao.class, GenrePrinterServiceImpl.class})
@ComponentScan("ru.otus.spring")
public class GenreDaoJpaTest {

	@Autowired
	private GenreDao genreDaoJpa;

	@Autowired
	private GenrePrinterServiceImpl genrePrinterService;

	public static int EXPECTED_GENRE_ALL = 4;

	public static String JENRE_TRAGEDY = "tragedy";
	
	@DisplayName("количество жанров в БД") 
	@Test
	public void returnCountGenre() {
		System.out.println("genreDaoJpa="+ genreDaoJpa);
		assertThat(genreDaoJpa.count()).isEqualTo(EXPECTED_GENRE_ALL);
	}

	@DisplayName("поиск жанра по ид") 
	@Test
	public void returnGenreByID() {
		Optional<Genre> genre = genreDaoJpa.findById(1L);
		System.out.println(genrePrinterService.printGenreToString(genre.get()));

		assertThat(genre.get()).hasFieldOrPropertyWithValue("name", JENRE_TRAGEDY);
	}

	@DisplayName("поиск жанра по ид книги") 
	@Test
	public void returnGenreByBookID() {
		//assertThat(genreDaoJpa.getGenreByBookId(1L)).hasFieldOrPropertyWithValue("name", JENRE_TRAGEDY);
	}
	
}
