package ru.otus.spring.domain;

public class Book {
    private final Long id;
    private final String name;
    private final Author  author;
    private final Genre genre;

    public Book(Long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        
    }

    public Book(String name, Author author, Genre genre) {
        this.id = null;
        this.name = name;
        this.author = author;
        this.genre = genre;
        
    }    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

	
	public Author getAuthor() {
		return author;
	}

	public Genre getGenre() {
		return genre;
	}

	public Long getAuthorid() {
		if(author!=null) {
			return author.getId();
		}
		return null;
	}
	public Long getGenreid() {
		if(genre!=null) {
			return genre.getId();
		}
		return null;
	}
	@Override
	public String toString(){
		return "[Book name="+name+", book id="+id+"]";
	}
}
