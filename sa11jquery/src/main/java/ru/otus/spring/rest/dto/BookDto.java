package ru.otus.spring.rest.dto;

import lombok.*;
import ru.otus.spring.domain.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;

    public static BookDto toDto(Book book) {
        AuthorDto authorDto = AuthorDto.toDto(book.getAuthor());
        GenreDto genreDto = GenreDto.toDto(book.getGenre());
        return new BookDto(book.getId(), book.getName(), authorDto, genreDto);
    }
}
