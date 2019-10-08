package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

public interface AuthorPrinterService {
    String printAuthorToString(Author author);
}
