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
@NamedEntityGraph(name = "GoodsGraph", attributeNodes = {@NamedAttributeNode(value = "goodsType"),
       @NamedAttributeNode(value = "brand"), @NamedAttributeNode(value = "provider"), @NamedAttributeNode(value = "store")})
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, unique = false)
    private String code;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "colour", nullable = true, unique = false)
    private String colour;
    @Column(name = "size", nullable = true, unique = false)
    private String size;
    @Column(name = "price", nullable = false, unique = false)
    private Double price;

    @ManyToOne(targetEntity = GoodsType.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "goodstypeid")
    private GoodsType  goodsType;


    @ManyToOne(targetEntity = Brand.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "brandid")
    private Brand  brand;

    @ManyToOne(targetEntity = Provider.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "providerid")
    private Provider  provider;

    @ManyToOne(targetEntity = Store.class, cascade = CascadeType.MERGE , fetch = FetchType.EAGER )
    @JoinColumn(name = "storeid")
    private Store store;

    public Goods(String code, String name, Double price) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Goods(String code, String name, Double price, GoodsType goodsType) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.goodsType = goodsType;
        this.price = price;
    }

    public Goods(String code, String name, Double price, GoodsType goodsType, Brand brand) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.goodsType = goodsType;
        this.brand = brand;
        this.price = price;
    }

    public Goods(String code, String name, GoodsType goodsType, Double price, Brand brand, Provider provider) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.goodsType = goodsType;
        this.brand = brand;
        this.provider = provider;
        this.price = price;
    }

    public Goods(String code, String name, GoodsType goodsType, Double price, Brand brand, Provider provider, Store store) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.goodsType = goodsType;
        this.brand = brand;
        this.provider = provider;
        this.store = store;
        this.price = price;

    }

}
