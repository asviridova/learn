package ru.otus.spring.orm.models;

import javax.persistence.*;

//https://www.baeldung.com/jpa-join-column
@Entity
@Table(name = "book")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


//    @ManyToOne(targetEntity = AuthorModel.class, cascade = CascadeType.ALL /*, fetch = FetchType.LAZY*/)
//    @JoinColumn(name = "authorid")
//    private AuthorModel author;
//
//    // Указывает на связь между таблицами "один к одному"
//    @OneToOne(targetEntity = GenreModel.class, cascade = CascadeType.ALL)
//    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
//    @JoinColumn(name = "genreid")
//    private GenreModel genreModel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


//	public AuthorModel getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(AuthorModel author) {
//		this.author = author;
//	}
//
//	public GenreModel getGenreModel() {
//		return genreModel;
//	}
//
//	public void setGenreModel(GenreModel genreModel) {
//		this.genreModel = genreModel;
//	}

    
    
}
