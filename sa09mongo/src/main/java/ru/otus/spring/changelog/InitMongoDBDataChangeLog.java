package ru.otus.spring.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Book;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Book book;

    @ChangeSet(order = "000", id = "dropDB", author = "asviridova", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initKnowledges", author = "stvort", runAlways = true)
    public void initBooks(MongoTemplate template){
        book = template.save(new Book("1", "Faust", "I.Goethe'", "tragedy"));
        book = template.save(new Book("2", "Midsummer nights dream", "W.Shakespeare", "comedy"));
        book = template.save(new Book("3", "Otello", "W.Shakespeare", "tragedy"));
        book = template.save(new Book("4", "Notre Dame", "V.Hugo", "prose"));
    }



}
