package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

   List<Book> findAll();

   List<Book> findAllByGenreId(String genreId);

   List<Book> findAllByGenreName(String genreName);

   List<Book> findAllByAuthorId(String authorId);


}

