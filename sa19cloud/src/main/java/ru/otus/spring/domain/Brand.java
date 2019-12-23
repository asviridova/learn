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
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "country", nullable = true, unique = false)
    private String country;

    public Brand(String name, String country) {
        this.id = null;
        this.name = name;
        this.country = country;

    }
}
