package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {
//public interface BookRepository extends CrudRepository<Book, String> {

   List<Book> findAll();

   List<Book> findAllByGenreId(Long genreId);

   List<Book> findAllByGenreName(String genreName);

   List<Book> findAllByAuthorId(Long authorId);


}

