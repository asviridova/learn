package ru.otus.spring.service;

import java.util.Scanner;

public class WriteReadServiceImpl implements WriteReadService {

    Scanner scanner = new Scanner(System.in);

    public void writeMessage(String message){
        System.out.println(message);
    }

    public String readMessage(){
        return scanner.next();
    }

}
