package ru.otus.spring.orm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.otus.spring.orm.models.AuthorModel;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

	@Override
	public Optional<AuthorModel> findById(long id) {
		return Optional.ofNullable(em.find(AuthorModel.class, id));
	}

	@Override
	public List<AuthorModel> findAll() {
		return em.createQuery("select s from author s", AuthorModel.class).getResultList();
	}


	@Override
	@Transactional  
	public AuthorModel save(AuthorModel author) {
		if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
	}


    @Override
    public List<AuthorModel> findAllWithEntityGraph() {
        EntityGraph<?> entityGraph = em.getEntityGraph("OtusStudentWithAvatarAndEmails");
        TypedQuery<AuthorModel> query = em.createQuery("select s from author s", AuthorModel.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<AuthorModel> findAllWithJoinFetch() {
        return em.createQuery("select distinct s from author s join fetch s.books", AuthorModel.class).getResultList();
    }

	
}