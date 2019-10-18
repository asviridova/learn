package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookDaoJPA implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        Long res = em.createQuery("select count(*) from Book s", Long.class).getSingleResult();
        return res == null ? 0 : res.intValue();
    }

    @Override
    @Transactional
    public Long insert(Book book) {
        if (book.getId()==null || book.getId() <= 0) {
            em.persist(book);
            return book.getId();
        } else {
            return em.merge(book).getId();
        }
    }

    @Override
    public Book getById(Long id) {
        //return em.find(Book.class, id);
        return em.createQuery("select distinct s from Book s join fetch s.author join fetch s.genre where s.id = :id", Book.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select distinct s from Book s join fetch s.author join fetch s.genre", Book.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from Book b where b.id = :id").setParameter("id", id).executeUpdate();
    }

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
