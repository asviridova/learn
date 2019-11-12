package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao){
        this.genreDao = genreDao;
    }

    @Override
    public long count() {
        return genreDao.count();
    }

    @Override
    public Long insert(Genre genre) {
        Genre genreNew = genreDao.save(genre);
        return genreNew.getId();
    }

    @Override
    public Optional<Genre> getById(Long id) {
        return genreDao.findById(id);
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> list = genreDao.findAll();
        return list;
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
