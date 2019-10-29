package ru.otus.spring.shell;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.AuthorPrinterService;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookPrinterService;
import ru.otus.spring.service.BookService;

@ShellComponent
@Slf4j
public class LibraryCommands {

    private final BookService bookService;
    private final BookPrinterService bookPrinterService;

    private final AuthorService authorService;
    private final AuthorPrinterService authorPrinterService;

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    public LibraryCommands(BookService bookService, BookPrinterService bookPrinterService, AuthorService authorService, AuthorPrinterService authorPrinterService) {
        this.bookService = bookService;
        this.bookPrinterService = bookPrinterService;
        this.authorService = authorService;
        this.authorPrinterService = authorPrinterService;
    }

    //Получение по идентификатору
    @ShellMethod(value = "getBooksById",  key ={ "book-id", "bid" })
    public String getBooksById(String bookId){
        return bookService.getById(bookId).map(bookPrinterService::printBookToString).orElseThrow(BookNotFoundException::new);
        //return bookPrinterService.printBookToString(bookService.getById(bookId).get());
    }

    //Получение всех сущностей
    @ShellMethod(value = "getAllBooks",  key ={ "books", "b" })
    public String getAllBooks(){
        return bookPrinterService.printBookListToString(bookService.getAll());

    }

    @ShellMethod(value = "getBooksByAuthorId",  key ={ "books-author", "baid" })
    public String getBooksByAuthorId(String authorId){
        return bookPrinterService.printBookListToString(bookService.getBooksByAuthorId(authorId));
    }

    @ShellMethod(value = "getBooksByGenreId",  key ={ "books-genre-id", "bgid" })
    public String getBooksByGenreId(String genreId){
        return bookPrinterService.printBookListToString(bookService.getBooksByGenreId(genreId));
    }

    @ShellMethod(value = "getBooksByGenre",  key ={ "books-genre", "bg" })
    public String getBooksByGenre(String genreName){
        return bookPrinterService.printBookListToString(bookService.getBooksByGenre(genreName));
    }

    //добавления
    @ShellMethod(value = "addBook",  key ={ "addbook", "ab" })
    public String addBook(String name, String authorId, String genreId){
        return bookService.insert(name, authorId, genreId);
    }

    //----Book
    @ShellMethod(value = "createAndDeleteBook",  key ={ "createdeletebook", "cdb" })
    public void createAndDeleteBook(){
        LOGGER.info(bookService.getAll().size());
        String id = bookService.insert("Rider", "1", "1");
        LOGGER.info(bookService.getAll().size());
        bookService.deleteById(id);
        LOGGER.info(bookService.getAll().size());
    }
    //Author
    @ShellMethod(value = "addAuthor",  key ={ "addauthor", "aa" })
    public String addAuthor(String name, String nationality){
        return authorService.insert(name, nationality);
    }

    @ShellMethod(value = "createAndDeleteAuthor",  key ={ "createdeleteauthor", "cda" })
    public void createAndDeleteAuthor(){
        Author author = new Author("M.Rid", "American");
        String id = authorService.insert(author);
        log.info(authorPrinterService.printAuthorToString(author));
        authorService.deleteById(id);
    }

    @ShellMethod(value = "deleteAuthor",  key ={ "delauthor", "da" })
    public void deleteAuthor(String id){
        authorService.deleteById(id);
    }

}

