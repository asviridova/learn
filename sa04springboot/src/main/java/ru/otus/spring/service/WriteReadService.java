package ru.otus.spring.service;

public interface WriteReadService {

    void writeMessage(String message);

    String readMessage();

}
