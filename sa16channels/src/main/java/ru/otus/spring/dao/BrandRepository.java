package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Brand;

import java.util.List;


public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findBrandByName(String name);

}
