package ru.otus.spring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

@RestController
@Slf4j
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public Flux<Genre> getAllGenres() {
        Flux<Genre> list = genreService.getAll();
        log.debug("Genres:"+list);
        return list;
    }



}
