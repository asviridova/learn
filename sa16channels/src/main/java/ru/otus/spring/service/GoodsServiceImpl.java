package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GoodsRepository;
import ru.otus.spring.domain.*;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    private GoodsRepository goodsRepository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public long count() {
        return goodsRepository.count();
    }

    @Override
    public Long insert(Goods goods) {
        Goods goodsNew = goodsRepository.save(goods);
        return goodsNew.getId();
    }

    @Override
    public Optional<Goods> getById(Long id) {
        return goodsRepository.findById(id);
    }

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public Long insert(String code, String name,  String colour, String size, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(code, name, colour, size, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }

    @Override
    public Long update(Long id, String code, String name, String colour, String size, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(id, code, name, colour, size, price, goodsType, brand, provider, store);
        Goods goodsNew = goodsRepository.save(goods);
        log.info("goods updated with id = " + id + ", name = " + name + ", price = " + price);
        return goodsNew.getId();
    }
}