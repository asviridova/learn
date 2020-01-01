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
@Table(name = "partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "inn", nullable = true, unique = false)
    private String inn;
    @Column(name = "address", nullable = true, unique = false)
    private String address;

    public Partner(String name) {
        this.id = null;
        this.name = name;
    }
    public Partner(String name, String inn) {
        this.id = null;
        this.name = name;
        this.inn = inn;
    }
    public Partner(String name, String inn, String address) {
        this.id = null;
        this.name = name;
        this.inn = inn;
        this.address = address;
    }
}
