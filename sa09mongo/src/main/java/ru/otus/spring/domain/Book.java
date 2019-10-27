package ru.otus.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "book")
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "book_sequence";

    @Id
    private Long id;
    private String name;
    private Author  author;
    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.id = -1L;
        this.name = name;
        this.author = author;
        this.genre = genre;

    }

    public Long getId() {
        return id;
    }
}
