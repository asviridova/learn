package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
