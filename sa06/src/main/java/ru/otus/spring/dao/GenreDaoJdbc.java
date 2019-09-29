package ru.otus.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@Repository
public class GenreDaoJdbc implements GenreDao {
    
	private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final JdbcOperations jdbc;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
    }
	
	@Override
	public int count() {
        return jdbc.queryForObject("select count(*) from genre", Integer.class);
	}
	
	@Override
	public void insert(Genre genre) {
		String sql = "insert into genre (`name`) values (:name)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", genre.getName());
		KeyHolder keyHolder = new GeneratedKeyHolder( );
		String[] keyColumnNames = new String[]{"id"};
		namedParameterJdbcOperations.update(sql, paramSource, keyHolder, keyColumnNames );
		
		Number key = keyHolder.getKey();
	    System.out.println("New generated id from keyholder: " + key.longValue());
	}
	
	
	@Override
	public Genre getById(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from genre where id = :id", params, new GenreMapper()
        );		
	}

	@Override
	public List<Genre> getAll() {
		return jdbc.query("select * from genre", new GenreMapper());
	}

	@Override
	public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from genre where id = :id", params
        );
	}

//	@Override
//	public List<Book> getBooks(Long genreid) {
//		Map<String, Object> params = Collections.singletonMap("id", genreid);
//		List<Book> books = namedParameterJdbcOperations.query(
//                "select * from book where genreid = :id", params, new GenreMapperBooks()
//        );		
//		return books;
//	}
//
    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
//	
//    private static class GenreMapperBooks implements RowMapper<Book> {
//
//        @Override
//        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//            Long id = resultSet.getLong("id");
//            String name = resultSet.getString("name");
//            Long authorid = resultSet.getLong("authorid");
//            Long genreid = resultSet.getLong("genreid");
//            Book b = new Book(id, name, authorid, genreid);
//            
//            return b;
//        }
//    }
//
//	@Override
//	public List<Book> getBooksByGenre(String genreName) {
//		Map<String, Object> params = Collections.singletonMap("genrename", genreName);
//		List<Book> books = namedParameterJdbcOperations.query(
//                "select * from book where genreid in (select id from genre where name = :genrename)", params, new GenreMapperBooks()
//        );		
//		return books;
//	}
	
}
