package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.GoodsType;

import java.util.Optional;

public interface GoodsTypeRepository extends JpaRepository<GoodsType, Long> {
    public GoodsType getByCode(String code);
}
