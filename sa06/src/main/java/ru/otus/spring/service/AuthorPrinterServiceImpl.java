package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;

import java.util.List;

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

    @Override
    public String printAuthorListToString(List<Author> authorList){
        if(authorList==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Author author: authorList){
            sb.append("Author{" +
                    "id=" + author.getId() +
                    ", name='" + author.getName() +
                    ", nationality=" + author.getNationality() +
                    '}');
            sb.append("\n");
        }
        return sb.toString();
    }
}
