package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Repository
public class AuthorDaoCustomImpl implements AuthorDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getAuthorByBookId(Long id) {
        TypedQuery<Author> query = em.createQuery("select distinct a from Author a where a.id in (select b.author.id from Book b where b.id = :id)", Author.class);
        return query.setParameter("id", id).getSingleResult();
    }

}
