package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Genre;

@Service
public class GenrePrinterServiceImpl implements  GenrePrinterService {
    @Override
    public String printGenreToString(Genre genre) {
        return "Genre{" +
                "id=" + genre.getId() +
                ", name='" + genre.getName() +
                '}';
    }
}
