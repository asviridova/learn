package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

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

    @Override
    public String printAuthorListToString(List<Book> bookList){
        if(bookList==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Book b: bookList){
            sb.append("Book{" +
                    "id=" + b.getId() +
                    ", name='" + b.getName() + '\'' +
                    ", author=" + b.getAuthor() +
                    ", genre=" + b.getGenre() +
                    '}');
            sb.append("\n");
        }
        return sb.toString();
    }

}
