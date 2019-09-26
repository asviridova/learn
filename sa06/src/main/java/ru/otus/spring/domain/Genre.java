package ru.otus.spring.domain;

public class Genre {

    private final Long id;
    private final String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
	@Override
	public String toString(){
		return "Genre name="+name+", genre id="+id;
	}
}
