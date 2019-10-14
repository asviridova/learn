package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao){
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return genreDao.count();
    }

    @Override
    public Long insert(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void deleteById(Long id) {
        genreDao.deleteById(id);
    }

    @Override
    public Genre getGenreByBookId(Long id) {
        return genreDao.getGenreByBookId(id);
    }
}
