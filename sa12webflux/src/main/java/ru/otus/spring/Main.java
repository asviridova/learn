package ru.otus.spring;

import com.fasterxml.classmate.GenericType;
import org.omg.CORBA.LongLongSeqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
@SpringBootApplication
public class Main {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        BookService bookService = context.getBean(BookService.class);

    //Книги
        bookService.getAll().subscribe(b -> System.out.println(b.toString()));
    //--- Авторы
        AuthorService authorService = context.getBean(AuthorService.class);

        authorService.getAll().subscribe(a -> System.out.println(a.toString()));

        //--- Жанры
        GenreService genreService = context.getBean(GenreService.class);

        genreService.getAll().subscribe(g -> System.out.println(g.toString()));

        //---- сохранение
        Mono<Book> bookNew =  bookService.insert("New book", "1", "1");
        bookNew.subscribe(b -> System.out.println("Saved book:"+b.toString()));

        Mono<Book> bookUpd =  bookService.update("1","Faust_2", "3", "1");
        bookUpd.subscribe(b -> System.out.println("Updated book:"+b.toString()));

    }
}
