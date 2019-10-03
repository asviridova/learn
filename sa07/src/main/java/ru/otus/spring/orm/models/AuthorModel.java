package ru.otus.spring.orm.models;

import java.util.List;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "author")
//@NamedEntityGraph(name = "AuthorBookGraph",
//attributeNodes = {@NamedAttributeNode(value = "id"),
//        @NamedAttributeNode(value = "books")})
public class AuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "nationality", nullable = true, unique = true)
    private String nationality;

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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

//    @OneToMany(targetEntity = BookModel.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER /*, mappedBy = "employee"*/)
//	@JoinColumns({ 
//		@JoinColumn(name = "id", referencedColumnName = "authorid"),
//	})
//    private List<BookModel> books;
    
    
}

