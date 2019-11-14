package ru.otus.spring;

import java.util.List;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorServiceImpl;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class);

    }
}
