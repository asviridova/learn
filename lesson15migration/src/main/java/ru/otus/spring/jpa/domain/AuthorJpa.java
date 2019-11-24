package ru.otus.spring.jpa.domain;

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
public class AuthorJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "nationality", nullable = true, unique = false)
    private String nationality;

    public AuthorJpa(String name, String nationality) {
        this.id = null;
        this.name = name;
        this.nationality = nationality;

    }
}
