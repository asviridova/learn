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
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "inn", nullable = true, unique = false)
    private String inn;
    @Column(name = "address", nullable = true, unique = false)
    private String address;
    @Column(name = "flagblacklist", nullable = true, unique = false)
    private Integer flagBlackList;

    public Provider(String name) {
        this.id = null;
        this.name = name;
    }
    public Provider(String name, String inn) {
        this.id = null;
        this.name = name;
        this.inn = inn;
    }
    public Provider(String name, String inn, String address) {
        this.id = null;
        this.name = name;
        this.inn = inn;
        this.address = address;
    }
}
