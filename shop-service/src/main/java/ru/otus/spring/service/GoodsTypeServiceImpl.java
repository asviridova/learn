package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GoodsTypeRepository;
import ru.otus.spring.domain.GoodsType;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GoodsTypeServiceImpl implements GoodsTypeService {

    private final GoodsTypeRepository goodsTypeRepository;

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

    @Override
    public GoodsType getByCode(String code){
        return  goodsTypeRepository.getByCode(code);
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public void deleteById(Long id) {
        goodsTypeRepository.deleteById(id);
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Long insert(String code, String name) {
        GoodsType goodsType = new GoodsType(code, name);
        GoodsType goodsTypeNew = goodsTypeRepository.save(goodsType);
        Long id = goodsTypeNew.getId();
        log.info("goodsType inserted with id = " + id + ", name = " + name);
        return id;
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Long update(Long id, String code, String name) {
        GoodsType goodsType = new GoodsType(id, code, name);
        GoodsType goodsTypeNew = goodsTypeRepository.save(goodsType);
        log.info("GoodsType updated with id = " + id + ", name = " + name );
        return goodsTypeNew.getId();
    }

}
