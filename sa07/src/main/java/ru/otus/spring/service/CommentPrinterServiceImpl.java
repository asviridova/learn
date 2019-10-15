package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.CommentBook;

import java.util.List;

@Service
public class CommentPrinterServiceImpl implements CommentPrinterService {
    @Override
    public String printCommentToString(CommentBook commentBook) {
        return "Comment{" +
                "id=" + commentBook.getId() +
                ", name='" + commentBook.getCommenttext() +
                ", book=" + commentBook.getBook() +
                '}';
    }

    @Override
    public String printCommentListToString(List<CommentBook> commentList) {
        if(commentList==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(CommentBook commentBook: commentList){
            sb.append("Comment{" +
                    "id=" + commentBook.getId() +
                    ", name='" + commentBook.getCommenttext() +
                    ", book=" + commentBook.getBook() +
                    '}');
            sb.append("\n");
        }
        return sb.toString();
    }
}

