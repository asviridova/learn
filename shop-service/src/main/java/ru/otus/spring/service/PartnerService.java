package ru.otus.spring.service;

import ru.otus.spring.domain.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    long count();

    Long insert(Partner provider) ;

    Optional<Partner> getById(Long id) ;

    List<Partner> getAll() ;
    void deleteById(Long id);

    Long insert(String name, String inn, String address) ;

    Long update(Long id, String name, String inn, String address) ;

}
