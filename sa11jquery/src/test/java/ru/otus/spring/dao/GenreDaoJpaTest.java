package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan("ru.otus.spring")
public class GenreDaoJpaTest {

	@Autowired
	private GenreDao genreDaoJpa;

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

		assertThat(genre.get()).hasFieldOrPropertyWithValue("name", JENRE_TRAGEDY);
	}


}
