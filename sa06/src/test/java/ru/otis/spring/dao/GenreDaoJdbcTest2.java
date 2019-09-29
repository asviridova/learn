package ru.otis.spring.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.dao.GenreDaoJdbc;

@DisplayName("DAO для работы с жанрами")
//@ExtendWith(SpringExtension.class) //JUNIT5
//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class) //Junit4
@JdbcTest
@Import(GenreDaoJdbc.class) 
@SpringBootTest(classes=GenreDaoJdbcTest2.class)
public class GenreDaoJdbcTest2 {
	
	@Autowired
	private GenreDaoJdbc genreDaoJdbc;
	
	@Autowired
	private BookDaoJdbc bookDaoJdbc;
	
	@Autowired
	private AuthorDaoJdbc authorDaoJdbc;
	
	public static int FOUR = 4;
	public static int TWO = 2;
	public static String JENRE_TRAGEDY = "tragedy";
	
	@DisplayName("количество жанров в БД") 
	@Test
	public void returnCountGenre() {
		System.out.println("genreDaoJdbc="+genreDaoJdbc);
		assertThat(genreDaoJdbc.count()).isEqualTo(FOUR);
	}

	@DisplayName("поиск жанра по ид") 
	@Test
	public void returnGenreByID() {
		assertThat(genreDaoJdbc.getById(1L)).hasFieldOrPropertyWithValue("name", JENRE_TRAGEDY);
	}

	@DisplayName("поиск количества книг по  жанру") 
	@Test
	public void returnBookCountByGenreID() {
		assertThat(bookDaoJdbc.getBooksByGenre("tragedy").size()).isEqualTo(TWO);
	}
	
}
