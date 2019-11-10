package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        if(name.equalsIgnoreCase("Ivan"))
        return new Person(name, 18);
        else
            return new Person(name, 28);
    }
}
