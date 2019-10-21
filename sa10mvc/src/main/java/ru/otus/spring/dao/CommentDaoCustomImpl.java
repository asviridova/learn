package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.CommentBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentDaoCustomImpl implements CommentDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CommentBook> getAllByBookId(Long bookId) {
        TypedQuery<CommentBook> query = em.createQuery("select distinct c from CommentBook c where c.book.id in (select b.id from Book b where b.id = :bookid)", CommentBook.class);
        return query.setParameter("bookid", bookId).getResultList();
    }

}
