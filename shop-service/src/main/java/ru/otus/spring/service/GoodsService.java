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
    Long insert(String code, String name,  String colour, String size, Integer quantity, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) ;
    Long insert(String code, String name,  String colour, String size, Integer quantity, Double price, Long goodsTypeId, Long brandId, Long providerId, Long storeId);
    Long update(Long id, String code, String name, String colour, String size, Integer quantity, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) ;
    void parseCsvFile(String fileContent);
}
