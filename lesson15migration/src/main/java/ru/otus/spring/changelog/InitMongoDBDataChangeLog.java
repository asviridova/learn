package ru.otus.spring.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

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
        authorShakespeare = template.save(new Author("1", "W.Shakespeare", "English"));
        authorHugo = template.save(new Author("2", "V.Hugo", "French"));
        authorGoethe = template.save(new Author("3", "I.Goethe", "Germany"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "asviridova", runAlways = true)
    public void initGenres(MongoTemplate template){
        genreTragedy = template.save(new Genre("1", "tragedy"));
        genreComedy = template.save(new Genre("2", "comedy"));
        genreProse = template.save(new Genre("3", "prose"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "asviridova", runAlways = true)
    public void initBooks(MongoTemplate template){

        book = template.save(new Book("1", "Faust", authorGoethe, genreTragedy));
        book = template.save(new Book("2", "Midsummer nights dream", authorShakespeare, genreComedy));
        book = template.save(new Book("3", "Otello", authorShakespeare, genreTragedy));
        book = template.save(new Book("4", "Notre Dame", authorHugo, genreProse));


    }


}
