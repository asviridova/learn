package ru.otus.spring.service;

import ru.otus.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    long count();

    Optional<Store> getById(Long id);

    List<Store> getAll();

    void deleteById(Long id);

    Long insert(String code, String address);

    Long update(Long id, String code, String address);
}
