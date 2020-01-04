package ru.otus.spring.service;

import ru.otus.spring.domain.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    long count();

    Long insert(Brand brand);

    Optional<Brand> getById(Long id);

    List<Brand> getAll();

    void deleteById(Long id);

    Long insert(String name, String country) ;

    Long update(Long id, String name, String country) ;

    Brand getByName(String name);

}
