package ru.otus.spring.dao;


import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations

@Repository
public class GenreDaoCustomImpl implements GenreDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre getGenreByBookId(Long id) {
        TypedQuery<Genre> query = em.createQuery("select distinct g from Genre g where g.id in (select b.genre.id from Book b where b.id = :id)", Genre.class);
        return query.setParameter("id", id).getSingleResult();
    }
}
