package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GoodsTypeRepository;
import ru.otus.spring.domain.GoodsType;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GoodsTypeServiceImpl implements GoodsTypeService {

    private GoodsTypeRepository goodsTypeRepository;

    @Autowired
    public GoodsTypeServiceImpl(GoodsTypeRepository goodsTypeRepository){
        this.goodsTypeRepository = goodsTypeRepository;
    }

    @Override
    public long count() {
        return goodsTypeRepository.count();
    }

    @Override
    public Optional<GoodsType> getById(Long id) {
        return goodsTypeRepository.findById(id);
    }

    @Override
    public List<GoodsType> getAll() {
        return goodsTypeRepository.findAll();
    }

}
