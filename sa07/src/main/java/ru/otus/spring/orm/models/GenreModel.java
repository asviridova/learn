package ru.otus.spring.orm.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

//    @OneToOne(targetEntity = BookModel.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER /*, mappedBy = "employee"*/)
//	@JoinColumns({ 
//		@JoinColumn(name = "id", referencedColumnName = "genreid"),
//	})
//    private List<BookModel> books;

    
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

//	public List<BookModel> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<BookModel> books) {
//		this.books = books;
//	}


}
