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
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, unique = false)
    private String code;
    @Column(name = "address", nullable = true, unique = false)
    private String address;

    public Store(String code, String address) {
        this.id = null;
        this.code = code;
        this.address = address;

    }
    public Store(String code) {
        this.id = null;
        this.code = code;
    }
}
