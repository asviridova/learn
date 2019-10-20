package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookDaoCustomImpl implements BookDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getBooksByAuthorId(Long authorid) {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b join fetch b.author join fetch b.genre where b.author.id in (select a.id from Author a where a.id = :authorid)", Book.class);
        return query.setParameter("authorid", authorid).getResultList();
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreid) {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b join fetch b.author join fetch b.genre where b.genre.id in (select g.id from Genre g where g.id = :genreid)", Book.class);
        return query.setParameter("genreid", genreid).getResultList();
    }

    @Override
    public List<Book> getBooksByGenre(String genrename) {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b join fetch b.author join fetch b.genre where b.genre.id in (select g.id from Genre g where g.name = :genrename)", Book.class);
        return query.setParameter("genrename", genrename).getResultList();
    }

}
