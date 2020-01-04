package ru.otus.spring.service;

import ru.otus.spring.domain.GoodsType;

import java.util.List;
import java.util.Optional;

public interface GoodsTypeService {
    long count();

    Optional<GoodsType> getById(Long id);

    List<GoodsType> getAll();

    void deleteById(Long id);

    Long insert(String code, String name);

    Long update(Long id, String code, String name);

    GoodsType getByCode(String code);
}
