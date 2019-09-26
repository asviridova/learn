package ru.otus.spring;

import java.util.List;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);
        
        //-----
        GenreDao genreDao = context.getBean(GenreDao.class);
        System.out.println("All count genre: " + genreDao.count());
        
        Genre genre1 = new Genre(10L, "mystic");
        genreDao.insert(genre1);
        
        System.out.println("All count genre2: " + genreDao.count());
        
        Genre comedy = genreDao.getById(2L);
        System.out.println("comedy genre id: " + comedy.getId() + " name: " + comedy.getName());
        
        List<Genre> listOfGenre = genreDao.getAll();
        System.out.println("listOfGenre"+listOfGenre);
        
        genreDao.deleteById(10L);
        System.out.println("All count genre3: " + genreDao.count());
        
        List<Book> books = genreDao.getBooks(1L);
        System.out.println("books: " + books);
        
        List<Book> books1 = genreDao.getBooksByGenre("tragedy");
        System.out.println("books tragedy: " + books1);
        
        //Console.main(args);
    }
}
