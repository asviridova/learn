package ru.otus.spring.orm.repository;

import java.util.List;
import java.util.Optional;

import ru.otus.spring.orm.models.AuthorModel;

public interface AuthorRepositoryJpa {
	
	Optional<AuthorModel> findById(long id);
    List<AuthorModel> findAll();

    List<AuthorModel> findAllWithJoinFetch();

    AuthorModel save(AuthorModel author);

    List<AuthorModel> findAllWithEntityGraph();
}
