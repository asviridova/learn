package ru.otus.spring.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.SequenceGeneratorService;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Book book;
    private Author authorGoethe;
    private Author authorShakespeare;
    private Author authorHugo;
    private Genre genreComedy;
    private Genre genreTragedy;
    private Genre genreProse;

    @ChangeSet(order = "000", id = "dropDB", author = "asviridova", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }


    @ChangeSet(order = "001", id = "initAuthors", author = "asviridova", runAlways = true)
    public void initAuthors(MongoTemplate template){
        authorShakespeare = template.save(new Author(1L, "W.Shakespeare", "English"));
        authorHugo = template.save(new Author(2L, "V.Hugo", "French"));
        authorGoethe = template.save(new Author(3L, "I.Goethe", "Germany"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "asviridova", runAlways = true)
    public void initGenres(MongoTemplate template){
        genreTragedy = template.save(new Genre(1L, "tragedy"));
        genreComedy = template.save(new Genre(2L, "comedy"));
        genreProse = template.save(new Genre(3L, "prose"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "asviridova", runAlways = true)
    public void initBooks(MongoTemplate template){
        book = template.save(new Book(1L, "Faust", authorGoethe, genreTragedy));
        book = template.save(new Book(2L, "Midsummer nights dream", authorShakespeare, genreComedy));
        book = template.save(new Book(3L, "Otello", authorShakespeare, genreTragedy));
        book = template.save(new Book(4L, "Notre Dame", authorHugo, genreProse));
//        book = template.save(new Book( "Faust", authorGoethe, genreTragedy));
//        book = template.save(new Book( "Midsummer nights dream", authorShakespeare, genreComedy));
//        book = template.save(new Book( "Otello", authorShakespeare, genreTragedy));
//        book = template.save(new Book( "Notre Dame", authorHugo, genreProse));

    }


}
