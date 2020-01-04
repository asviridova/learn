package ru.otus.spring.service;

import ru.otus.spring.domain.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    long count();

    Long insert(Provider provider) ;

    Optional<Provider> getById(Long id) ;

    List<Provider> getAll() ;
    void deleteById(Long id);

    Long insert(String name, String inn, String address) ;

    Long update(Long id, String name, String inn, String address) ;
    Provider getByName(String name);
}
