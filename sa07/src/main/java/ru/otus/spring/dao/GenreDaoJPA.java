package ru.otus.spring.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreDaoJPA implements  GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        Long res = em.createQuery("select count(*) from Genre s", Long.class).getSingleResult();
        return res == null ? 0 : res.intValue();
    }

    @Override
    @Transactional
    public Long insert(Genre genre)
    {
        if (genre.getId()==null || genre.getId() <= 0) {
            em.persist(genre);
            return genre.getId();
        } else {
            return em.merge(genre).getId();
        }
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select s from Genre s", Genre.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.createQuery("delete from Genre b where b.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Genre getGenreByBookId(Long id) {
        TypedQuery<Genre> query = em.createQuery("select distinct g from Genre g where g.id in (select b.genre.id from Book b where b.id = :id)", Genre.class);
        return query.setParameter("id", id).getSingleResult();
    }
}
