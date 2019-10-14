package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public Long insert(Author author) {
        return null;
    }

    @Override
    public Author getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void deleteById(Long id) {
        authorDao.deleteById(id);
    }

    @Override
    public Author getAuthorByBookId(Long id) {
        return authorDao.getAuthorByBookId(id);
    }
}
