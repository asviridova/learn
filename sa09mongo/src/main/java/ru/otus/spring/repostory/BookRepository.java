package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Person;

import java.util.List;

/*public interface BookRepository extends MongoRepository<Book, String> {
    //List<Book> findAllBy

}*/
public interface BookRepository extends CrudRepository<Book, String> {

    List<Book> findAll();
}

