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
@Table(name = "goodstype")
public class GoodsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, unique = false)
    private String code;
    @Column(name = "name", nullable = true, unique = false)
    private String name;

    public GoodsType(String code, String name) {
        this.id = null;
        this.code = code;
        this.name = name;

    }
    public GoodsType(String code) {
        this.id = null;
        this.code = code;
    }
}