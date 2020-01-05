package ru.otus.spring.exception;

public class IncorrectFormatException extends Exception {
    public IncorrectFormatException(String message) {
        super(message);
    }

    public IncorrectFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
