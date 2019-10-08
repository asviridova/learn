package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;

@Service
public class AuthorPrinterServiceImpl implements  AuthorPrinterService {
    @Override
    public String printAuthorToString(Author author) {
        return "Author{" +
                "id=" + author.getId() +
                ", name='" + author.getName() +
                ", nationality=" + author.getNationality() +
                '}';
    }
}
