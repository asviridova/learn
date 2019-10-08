package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;

@Service
public class BookPrinterServiceImpl implements BookPrinterService {
    @Override
    public String printBookToString(Book b) {
        return "Book{" +
                "id=" + b.getId() +
                ", name='" + b.getName() + '\'' +
                ", author=" + b.getAuthor() +
                ", genre=" + b.getGenre() +
                '}';
    }
}
