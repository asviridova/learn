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
@Table(name = "shoes")
public class Shoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, unique = false)
    private String code;
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @ManyToOne(targetEntity = Brand.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "brandid")
    private Brand  brand;

    @ManyToOne(targetEntity = Provider.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "providerid")
    private Provider  provider;

    @ManyToOne(targetEntity = Store.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "storeid")
    private Store store;

    public Shoes(String code, String name) {
        this.id = null;
        this.code = code;
        this.name = name;
    }

    public Shoes(String code, String name, Brand brand) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.brand = brand;
    }

    public Shoes(String code, String name, Brand brand, Provider provider) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.provider = provider;
    }

}
