package ru.otus.spring.repostory;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.ForeignKeyException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        List<Book> list = bookRepository.findAllByAuthorId(id);
        if(list!=null && !list.isEmpty()){
            throw new ForeignKeyException("ForeignKeyException!");
        }
    }
}
