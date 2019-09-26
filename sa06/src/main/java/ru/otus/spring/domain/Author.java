package ru.otus.spring.domain;

public class Author {
    private final Long id;
    private final String name;
    private final String nationality;
    

    public Author(Long id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

	public String getNationality() {
		return nationality;
	}

}
