package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long> {
    public Brand getByName(String name);
}
