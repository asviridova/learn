package ru.otus.spring.rest.dto;

import lombok.*;
import ru.otus.spring.domain.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class AuthorDto {
    private Long id;

    private String name;

    private String nationality;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getNationality());
    }
}
