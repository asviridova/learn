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
@Table(name = "commentbook")
public class CommentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "commenttext", nullable = false, unique = false)
    private String commenttext;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER /*, fetch = FetchType.LAZY*/ )
    @JoinColumn(name = "bookid")
    private Book  book;

    public CommentBook(String commenttext, Book book) {
        this.id = null;
        this.commenttext = commenttext;
        this.book = book;
    }
}
