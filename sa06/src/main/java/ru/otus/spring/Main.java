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

        //Book
        BookDao bookDao = context.getBean(BookDao.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);

        List<Book> books = bookDao.getBooksByGenreId(1L);
        System.out.println("books: " + books);
        
        List<Book> books1 = bookDao.getBooksByGenre("tragedy");
        System.out.println("books tragedy: " + books1);
        
        System.out.println("All count books: " + bookDao.count());
        
        Author author_1 = authorDao.getById(1L);
        Genre genre_1 = genreDao.getById(1L);
        Book book = new Book(null, "Gamlet", author_1, genre_1);
        bookDao.insert(book);
        
        System.out.println("All count books2: " + bookDao.count());
        bookDao.deleteById(1L);
        
        List<Book> listOfBook = bookDao.getAll();
        System.out.println("listOfBook"+listOfBook);
        
        Book book_by_id = bookDao.getById(2L);
        System.out.println("book_by_id="+book_by_id);
        
        Author author = bookDao.getAuthorByBookId(2L);
        System.out.println("author="+author);
        
        Genre genre_book = bookDao.getGenreByBookId(2L);
        
        System.out.println("genre_book="+genre_book);

        //Author
        
        System.out.println(authorDao.getAll());
        
        List<Book> books_by_author = bookDao.getBooksByAuthorId(1L);
        if(books_by_author!=null) {
        	System.out.println(books_by_author);
        	if(!books_by_author.isEmpty()) {
        		Book b = books_by_author.get(0);
        		System.out.println("******");
        		System.out.println(b);
        		System.out.println(b.getAuthor());
        		System.out.println(b.getGenre());
        	}
        }
        
        
       // Console.main(args);
    }
}
