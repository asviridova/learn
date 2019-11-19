package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.GenreRepository;

@Service
@Slf4j
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Flux<Genre> getAll() {
        return genreRepository.findAll();
    }

}
