package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.CommentBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentDaoJPA implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Long insert(CommentBook commentBook) {
        if (commentBook.getId()==null || commentBook.getId() <= 0) {
            em.persist(commentBook);
            return commentBook.getId();
        } else {
            return em.merge(commentBook).getId();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CommentBook commentBook = em.find(CommentBook.class, id);
        if (commentBook != null) {
            em.remove(commentBook);
        }
    }

    @Override
    public List<CommentBook> getAllByBookId(Long bookId) {
        TypedQuery<CommentBook> query = em.createQuery("select distinct c from CommentBook c where c.book.id in (select b.id from Book b where b.id = :bookid)", CommentBook.class);
        return query.setParameter("bookid", bookId).getResultList();
    }
}
