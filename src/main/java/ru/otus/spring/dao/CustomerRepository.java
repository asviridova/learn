package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    List<Customer> findAll();

    @RestResource(path = "names", rel = "names")
    List<Customer> findByName(String name);
}

