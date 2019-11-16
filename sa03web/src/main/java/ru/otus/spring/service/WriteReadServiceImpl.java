package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class WriteReadServiceImpl implements WriteReadService {

    private final Scanner scanner = new Scanner(System.in);

    public void writeMessage(String message){
        System.out.println(message);
    }

    public String readMessage(){
        return scanner.next();
    }

}
