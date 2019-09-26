package ru.otus.spring.domain;

public class Book {
    private final Long id;
    private final String name;
    private final Long authorid;
    private final Long genreid;

    public Book(Long id, String name, Long authorid, Long genreid) {
        this.id = id;
        this.name = name;
        this.authorid = authorid;
        this.genreid = genreid;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
