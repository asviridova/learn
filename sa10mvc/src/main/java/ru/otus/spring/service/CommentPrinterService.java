package ru.otus.spring.service;

import ru.otus.spring.domain.CommentBook;

import java.util.List;

public interface CommentPrinterService {

    String printCommentToString(CommentBook commentBook);

    String printCommentListToString(List<CommentBook> commentList);
    String printCommentListToString(Iterable<CommentBook> commentList);
}
