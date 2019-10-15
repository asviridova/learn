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
        return em.createQuery("select s from Book s", Book.class).getResultList().size();
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
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select s from Book s", Book.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorid) {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b where b.author.id in (select a.id from Author a where a.id = :authorid)", Book.class);
        return query.setParameter("authorid", authorid).getResultList();
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreid) {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b where b.genre.id in (select g.id from Genre g where g.id = :genreid)", Book.class);
        return query.setParameter("genreid", genreid).getResultList();
    }

    @Override
    public List<Book> getBooksByGenre(String genrename) {
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b where b.genre.id in (select g.id from Genre g where g.name = :genrename)", Book.class);
        return query.setParameter("genrename", genrename).getResultList();
    }
}
