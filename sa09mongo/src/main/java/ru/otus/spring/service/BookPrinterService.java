package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookPrinterService {
    String printBookToString(Book b);
    String printBookListToString(List<Book> bookList);
    String printBookListToString(Iterable<Book> bookList);
}
