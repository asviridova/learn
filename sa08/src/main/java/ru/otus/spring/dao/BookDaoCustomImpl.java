package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.*;
import java.util.List;

@Repository
public class BookDaoCustomImpl implements BookDaoCustom {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Book> findAllWithEntityGraph() {
        EntityGraph<?> entityGraph = em.getEntityGraph("BookGraph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }


}
