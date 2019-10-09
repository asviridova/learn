package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class GenrePrinterServiceImpl implements  GenrePrinterService {
    @Override
    public String printGenreToString(Genre genre) {
        return "Genre{" +
                "id=" + genre.getId() +
                ", name='" + genre.getName() +
                '}';
    }

    @Override
    public String printGenreListToString(List<Genre> genreList){
        if(genreList==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Genre genre: genreList){
            sb.append("Genre{" +
                    "id=" + genre.getId() +
                    ", name='" + genre.getName() +
                    '}');
            sb.append("\n");
        }
        return sb.toString();
    }
}
