package ru.otus.spring.exception;

public class BookNotFoundException  extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found!");
    }
}
