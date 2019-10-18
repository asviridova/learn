package ru.otus.spring.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.CommentBook;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class CommentServiceImpl implements  CommentService {
    private final CommentDao commentDao;
    private static final Logger LOGGER = LogManager.getLogger();
    private final BookService bookService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, BookService bookService){
        this.commentDao = commentDao;
        this.bookService = bookService;
    }

    @Override
    public Long insert(Long bookId, String comment) {
        Book book = bookService.getById(bookId);
        if(book!=null) {
            CommentBook commentBook = new CommentBook(comment, book);
            Long id = commentDao.insert(commentBook);
            LOGGER.info("Comment inserted with id = "+id+", commentBook = "+comment);
            return id;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        commentDao.delete(id);
    }

    @Override
    public List<CommentBook> getAll(Long bookId) {
        return commentDao.getAllByBookId(bookId);
    }
}
