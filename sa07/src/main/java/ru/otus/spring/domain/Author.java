package ru.otus.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "nationality", nullable = true, unique = false)
    private String nationality;

    //@OneToMany(mappedBy = "author")
    //private List<Book> books;
    public Author(String name, String nationality) {
        this.id = null;
        this.name = name;
        this.nationality = nationality;

    }
}
