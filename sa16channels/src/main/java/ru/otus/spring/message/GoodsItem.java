package ru.otus.spring.message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.domain.Brand;
import ru.otus.spring.domain.GoodsType;
import ru.otus.spring.domain.Provider;

@Getter
@Setter
@Slf4j
@Data
public class GoodsItem {

    private String code;
    private String name;
    private String colour;
    private String size;
    private Double price;

    private String brandName;
    private String providerName;

    private Provider provider;
    private Brand brand;
    private GoodsType goodsType;

    private boolean filtered;

}
