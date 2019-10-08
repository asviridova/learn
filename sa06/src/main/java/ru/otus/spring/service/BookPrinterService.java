package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

public interface BookPrinterService {
    String printBookToString(Book b);

}
