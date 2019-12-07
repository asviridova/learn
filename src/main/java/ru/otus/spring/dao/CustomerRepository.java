package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
