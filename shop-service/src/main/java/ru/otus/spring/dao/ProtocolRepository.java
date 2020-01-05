package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Protocol;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

}