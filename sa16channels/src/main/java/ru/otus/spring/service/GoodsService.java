package ru.otus.spring.service;

import ru.otus.spring.domain.*;

import java.util.List;
import java.util.Optional;

public interface GoodsService {

    long count();
    Long insert(Goods goods) ;
    Optional<Goods> getById(Long id);
    List<Goods> getAll();
    void deleteById(Long id);
    Long insert(String code, String name,  String colour, String size, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) ;
    Long update(Long id, String code, String name, String colour, String size, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) ;

}
