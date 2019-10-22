package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "BookGraph",
        attributeNodes = {@NamedAttributeNode(value = "author"),
                @NamedAttributeNode(value = "genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER /*, fetch = FetchType.LAZY*/ )
    @JoinColumn(name = "authorid")
    private Author  author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "genreid")
    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.id = null;
        this.name = name;
        this.author = author;
        this.genre = genre;

    }
}
