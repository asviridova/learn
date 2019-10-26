package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.PersonRepository;

import java.util.Optional;

@EnableMongoRepositories
@SpringBootApplication
public class Main {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

        //PersonRepository repository = context.getBean(PersonRepository.class);

        //repository.save(new Person("Pushkin"));

        //Thread.sleep(3000);

        //repository.findAll().forEach(p -> System.out.println(p.getName()));

        //--------
        BookRepository bookRepository = context.getBean(BookRepository.class);
        bookRepository.findAll().forEach(book -> System.out.println(book.getName()));

        bookRepository.save(new Book("10", "Horse Rider", "M.Rid", "prose"));
        Thread.sleep(3000);
        Optional<Book> book1 = bookRepository.findById("10");
        System.out.println("New book:"+book1.get().getName());
    }
}
