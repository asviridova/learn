package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repostory.AuthorRepository;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public String insert(Author author) {
        Author authorNew = authorRepository.save(author);
        return authorNew.getId();
    }

    @Override
    public String insert(String name, String nationality) {
        Author author = new Author(name, nationality);
        Author authorNew = authorRepository.save(author);
        String id = authorNew.getId();
        log.info("Author inserted with id = " + id + ", name = " + name + ", nationality = " + nationality);
        return id;
    }
}
