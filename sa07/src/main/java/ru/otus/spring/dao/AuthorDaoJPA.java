package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

//https://www.baeldung.com/jpa-join-types

//https://www.objectdb.com/java/jpa/query/jpql/where

//https://www.baeldung.com/spring-data-jpa-query

//https://www.baeldung.com/jpa-query-parameters

@Repository
public class AuthorDaoJPA implements AuthorDao{

    @PersistenceContext
    private EntityManager em;


    @Override
    public int count() {
        return em.createQuery("select s from Author s", Author.class).getResultList().size();
    }

    @Override
    @Transactional
    public Long insert(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            em.flush();
            return author.getId();
        } else {
            return em.merge(author).getId();
        }
    }

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select s from Author s", Author.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Author author = em.find(Author.class, id);
        if (author != null) {
            em.remove(author);
        }
    }


    @Override
    public Author getAuthorByBookId(Long id) {
        TypedQuery<Author> query = em.createQuery("select distinct a from Author a where a.id in (select b.author.id from Book b where b.id = :id)", Author.class);
        return query.setParameter("id", id).getSingleResult();
    }

}
