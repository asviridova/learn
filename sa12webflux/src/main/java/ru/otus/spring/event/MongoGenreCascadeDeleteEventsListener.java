package ru.otus.spring.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.ForeignKeyException;
import ru.otus.spring.repostory.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoGenreCascadeDeleteEventsListener extends AbstractMongoEventListener<Genre> {

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        Flux<Book> list = bookRepository.findAllByGenreId(id);
        list.subscribe(b -> {
            throw new ForeignKeyException("ForeignKeyException!");
        });
    }


}
