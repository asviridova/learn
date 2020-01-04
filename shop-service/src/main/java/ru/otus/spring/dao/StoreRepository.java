package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    public Store getByCode(String code);
}
