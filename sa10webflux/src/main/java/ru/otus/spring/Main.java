package ru.otus.spring;

import org.omg.CORBA.LongLongSeqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
@SpringBootApplication
public class Main {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

//        BookService bookService = context.getBean(BookService.class);
//
//        bookService.getAll().forEach(book -> System.out.println(book.getName()+", id="+book.getId()));
//        //-----
//
//    //        Long id = bookService.insert("AAA", 1L, 1L);
//        String id = bookService.insert("AAA", "1", "1");
//
//        System.out.println("NEW ID="+id);
//
//        String id2 = bookService.insert("BBB", "1", "1");
//
//        System.out.println("NEW ID2="+id2);
//
//
//        Optional<Book> book1 = bookService.getById(id);
//        System.out.println("Added book:"+book1.get().getName()+", id="+book1.get().getId());
//
//        bookService.getAll().forEach(book -> System.out.println(book.getName()+", id="+book.getId()));

        //--------
       /* BookRepository bookRepository = context.getBean(BookRepository.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);


        bookRepository.findAll().forEach(book -> System.out.println(book.getName()));

        authorRepository.findAll().forEach(a -> System.out.println(a.getName()));

        genreRepository.findAll().forEach(g -> System.out.println(g.getName()));

        //----
        Optional<Author> authorFound = authorRepository.findById("1");

        Optional<Genre> genreFound = genreRepository.findById("1");
        if(authorFound.isPresent() && genreFound.isPresent()){
            bookRepository.save(new Book("10", "Horse Rider", authorFound.get(), genreFound.get()));
        }

        Thread.sleep(3000);

        Optional<Book> book1 = bookRepository.findById("10");
        System.out.println("Added book:"+book1.get().getName());


        bookRepository.deleteById("10");
        Optional<Book> book2 = bookRepository.findById("10");
        System.out.println("Removed book:"+book2.isPresent());

        List<Book> booksTragedy = bookRepository.findAllByGenreId("1");
        System.out.println("booksTragedy :"+booksTragedy.size());

        List<Book> booksComedy = bookRepository.findAllByGenreName("comedy");
        System.out.println("booksComedy :"+booksComedy.size());

        List<Book> booksSheakspere = bookRepository.findAllByAuthorId("1");
        System.out.println("booksSheakspere :"+booksSheakspere.size());
*/

    }
}
