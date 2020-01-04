package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    public Provider getByName(String name);
}
