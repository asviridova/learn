package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.AuthorRepository;
import ru.otus.spring.repostory.BookRepository;
import ru.otus.spring.repostory.GenreRepository;

import java.util.List;
import java.util.Optional;

//https://vertex-academy.com/tutorials/ru/java-8-stream-flatmap/
//https://habr.com/ru/company/luxoft/blog/270383/

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository ){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public Mono<Book> insert(String name, String authorId, String genreId) {
        return authorRepository.findById(authorId).flatMap(a ->
                genreRepository.findById(genreId).map(g -> new Book(name, a, g))
        ).flatMap(bookRepository::save);
    }

//    @Override
//    public void insert(String name, String authorId, String genreId) {
//        authorRepository.findById(authorId).subscribe(author -> {
//            System.out.println(author.toString());
//            genreRepository.findById(genreId).subscribe(genre -> {
//                System.out.println(genre.toString());
//                Book book = new Book(name, author, genre);
//                Mono<Book> bookNewMono = bookRepository.save(book);
//                System.out.println(bookNewMono.subscribe(b -> System.out.println(b.toString())));
//            });
//        });
//    }

//    @Override
//    public void update(String id, String name, String authorId, String genreId) {
//        authorRepository.findById(authorId).subscribe(author -> {
//            System.out.println(author.toString());
//            genreRepository.findById(genreId).subscribe(genre -> {
//                System.out.println(genre.toString());
//                Book book = new Book(id, name, author, genre);
//                Mono<Book> bookNewMono = bookRepository.save(book);
//                System.out.println(bookNewMono.subscribe(b -> System.out.println(b.toString())));
//            });
//        });
//    }

    @Override
    public Mono<Book> update(String id, String name, String authorId, String genreId) {
        return authorRepository.findById(authorId).flatMap(a ->
                genreRepository.findById(genreId).map(g -> new Book(id, name, a, g))
        ).flatMap(bookRepository::save);
    }


    @Override
    public Mono<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Flux<Book> getBooksByAuthorId(String authorid) {
        return bookRepository.findAllByAuthorId(authorid);
    }

    @Override
    public Flux<Book> getBooksByGenreId(String genreid) {
        return bookRepository.findAllByGenreId(genreid);
    }

    @Override
    public Flux<Book> getBooksByGenre(String genreName) {
        Mono<Genre> genreMono = genreRepository.findByName(genreName);
        Genre genre = genreMono.block();
        if(genre!=null) {
            return bookRepository.findAllByGenreId(genre.getId());
        }
        return null;
    }
}
