package ru.otus.spring.exception;

//@ResponseStatus(value= HttpStatus.CONFLICT, reason="ForeignKeyException")
public class ForeignKeyException  extends RuntimeException{

    public ForeignKeyException(String message) {
        super(message);
    }

    public ForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
