package ru.otus.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@Repository
public class BookDaoJdbc implements BookDao {
	
	private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final JdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
    }
	
	@Override
	public int count() {
		return jdbc.queryForObject("select count(*) from book", Integer.class);
	}

	@Override
	public void insert(Book book) {
		String sql = "insert into book (`name`, `authorid`, `genreid`) values (:name, :authorid, :genreid)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", book.getName());
		paramSource.addValue("authorid", book.getAuthorid());
		paramSource.addValue("genreid", book.getGenreid());
		KeyHolder keyHolder = new GeneratedKeyHolder( );
		String[] keyColumnNames = new String[]{"id"};
		namedParameterJdbcOperations.update(sql, paramSource, keyHolder, keyColumnNames );
		
		Number key = keyHolder.getKey();
	    System.out.println("New book generated id from keyholder: " + key.longValue());
	}

	@Override
	public Book getById(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		Book book = namedParameterJdbcOperations.queryForObject(
                "select b.id, b.name, b.authorid, b.genreid, a.name as authorname, a.nationality as authornationality, g.name as genrename "
		+" from book b "
		+"  inner join author a on b.authorid = a.id "
		+" inner join genre g on b.genreid = g.id "
		+" where b.id  = :id", params, new BookMapperComplex()
        );	

		return book;
	}

	@Override
	public List<Book> getAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Book> bookList = namedParameterJdbcOperations.query(
                "select b.id, b.name, b.authorid, b.genreid, a.name as authorname, a.nationality as authornationality, g.name as genrename "
		+" from book b "
		+"  inner join author a on b.authorid = a.id "
		+" inner join genre g on b.genreid = g.id ", params, new BookMapperComplex()
        );	
		return bookList;
		
	}

	@Override
	public void deleteById(Long id) {
		 Map<String, Object> params = Collections.singletonMap("id", id);
	        namedParameterJdbcOperations.update(
	                "delete from book where id = :id", params
	        );
		
	}

	@Override
	public Genre getGenreByBookId(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		List<Genre> genreList = namedParameterJdbcOperations.query(
                "select * from genre where id in (select genreid from book where id = :id )", params, new MapperBooksByGenre()
        );	
		if(genreList!=null) {
			return genreList.get(0);
		}
		return null;
	}

	@Override
	public Author getAuthorByBookId(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		List<Author> authorList = namedParameterJdbcOperations.query(
                "select * from author where id in (select authorid from book where id = :id )", params, new MapperBooksByAuthor()
        );	
		if(authorList!=null) {
			return authorList.get(0);
		}
		return null;
	}

	@Override
	public List<Book> getBooksByAuthorId(Long authorid) {
		Map<String, Object> params = Collections.singletonMap("authorid", authorid);
		List<Book> bookList = namedParameterJdbcOperations.query(
                "select b.id, b.name, b.authorid, b.genreid, a.name as authorname, a.nationality as authornationality, g.name as genrename "
		+" from book b "
		+"  inner join author a on b.authorid = a.id "
		+" inner join genre g on b.genreid = g.id "
		+" where b.authorid  = :authorid", params, new BookMapperComplex()
        );	
		return bookList;
	}
	
	

	private static class BookMapperComplex implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Long authorid = resultSet.getLong("authorid");
            Long genreid = resultSet.getLong("genreid");

            String authorname = resultSet.getString("authorname");
            String authornationality = resultSet.getString("authornationality");
            String genrename = resultSet.getString("genrename");
            
            Genre g = new Genre (genreid, genrename); 
            Author a = new Author (authorid, authorname, authornationality);
            
            return new Book(id, name, a, g);
        }
    }
	
	private static class MapperBooksByGenre implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Genre b = new Genre(id, name);
            return b;
        }
    }

	private static class MapperBooksByAuthor implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String nationality = resultSet.getString("nationality");
            Author b = new Author(id, name, nationality);
            return b;
        }
    }
	
	//-------------------------
	@Override
	public List<Book> getBooksByGenreId(Long genreid) {
		Map<String, Object> params = Collections.singletonMap("genreid", genreid);
		
		List<Book> bookList = namedParameterJdbcOperations.query(
                "select b.id, b.name, b.authorid, b.genreid, a.name as authorname, a.nationality as authornationality, g.name as genrename "
		+" from book b "
		+"  inner join author a on b.authorid = a.id "
		+" inner join genre g on b.genreid = g.id "
		+" where b.genreid  = :genreid", params, new BookMapperComplex()
        );	
		
		return bookList;
	}
	
	@Override
	public List<Book> getBooksByGenre(String genreName) {
		Map<String, Object> params = Collections.singletonMap("genrename", genreName);
		List<Book> bookList = namedParameterJdbcOperations.query(
                "select b.id, b.name, b.authorid, b.genreid, a.name as authorname, a.nationality as authornationality, g.name as genrename "
		+" from book b "
		+"  inner join author a on b.authorid = a.id "
		+" inner join genre g on b.genreid = g.id "
		+" where g.name  = :genrename", params, new BookMapperComplex()
        );	
		
		return bookList;
	}
	

	
	
}
