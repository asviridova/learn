package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Goods;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @EntityGraph(value = "GoodsGraph")
    List<Goods> findAll();


}