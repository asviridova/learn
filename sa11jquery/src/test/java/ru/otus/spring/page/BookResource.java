package ru.otus.spring.page;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Value
public class BookResource {

    @NotNull
    private final String id;

    @Null
    private final String name;


}