package ru.otus.spring.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.spring.domain.Brand;
import ru.otus.spring.domain.GoodsType;
import ru.otus.spring.domain.Provider;
import ru.otus.spring.domain.Store;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoodsStruct {

    private String code;
    private String name;
    private String colour;
    private String size;
    private Integer quantity;
    private Double price;

    private String goodsTypeCode;
    private String brandName;
    private String providerName;
    private String storeCode;

    private GoodsType goodsType;
    private Brand brand;
    private Provider provider;
    private Store store;

    public GoodsStruct(String code, String name, String colour, String size, Integer quantity, Double price, String goodsTypeCode,
                       String brandName, String providerName, String storeCode ){
        this.code = code;
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.goodsTypeCode = goodsTypeCode;
        this.brandName = brandName;
        this.providerName = providerName;
        this.storeCode = storeCode;

    }
}
