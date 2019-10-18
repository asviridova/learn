package ru.otus.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private static final Logger LOGGER = LogManager.getLogger();

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
        return authorDao.insert(author);
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

    public Long insert(String name, String nationality) {
        Author author = new Author(name, nationality);
        Long id = authorDao.insert(author);
        LOGGER.info("Author inserted with id = " + id + ", name = " + name + ", nationality = " + nationality);
        return id;
    }

}
