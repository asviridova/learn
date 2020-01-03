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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", nullable = false, unique = false)
    private String login;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "phone", nullable = false, unique = false)
    private String phone;

    public Customer(String login, String name, String phone) {
        this.id = null;
        this.name = name;
        this.login = login;
        this.phone = phone;

    }
}
