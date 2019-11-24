package ru.otus.spring.jpa.domain;

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
public class BookJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @ManyToOne(targetEntity = AuthorJpa.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER /*, fetch = FetchType.LAZY*/ )
    @JoinColumn(name = "authorid")
    private AuthorJpa  author;

    @ManyToOne(targetEntity = GenreJpa.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "genreid")
    private GenreJpa genre;

    public BookJpa(String name, AuthorJpa author, GenreJpa genre) {
        this.id = null;
        this.name = name;
        this.author = author;
        this.genre = genre;

    }
}
