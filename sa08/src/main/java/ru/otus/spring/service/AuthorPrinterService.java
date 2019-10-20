package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorPrinterService {
    String printAuthorToString(Author author);

    String printAuthorListToString(List<Author> authorList);

    String printAuthorListToString(Iterable<Author> authorList);
}
