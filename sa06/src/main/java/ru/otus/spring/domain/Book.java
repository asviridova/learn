package ru.otus.spring.domain;

public class Book {
    private final Long id;
    private final String name;
    private final Author  author;
    private final Genre genre;
    private final Long authorid;
    private final Long genreid;

    public Book(Long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        authorid = author!=null?author.getId():null;
        genreid = genre!=null?genre.getId():null;
        
    }
    public Book(Long id, String name, Long authorid, Long genreid) {
        this.id = id;
        this.name = name;
        this.author = null;
        this.genre = null;
        this.authorid = authorid;
        this.genreid = genreid;
        
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
		return authorid;
	}
	public Long getGenreid() {
		return genreid;
	}
	@Override
	public String toString(){
		return "[Book name="+name+", book id="+id+"]";
	}
}
