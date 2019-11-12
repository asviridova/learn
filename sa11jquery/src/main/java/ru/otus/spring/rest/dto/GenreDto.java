package ru.otus.spring.rest.dto;

import lombok.*;
import ru.otus.spring.domain.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GenreDto {
    private Long id;
    private String name;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

}
