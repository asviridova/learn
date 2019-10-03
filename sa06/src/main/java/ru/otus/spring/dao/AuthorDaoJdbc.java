package ru.otus.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
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

@Repository
public class AuthorDaoJdbc implements AuthorDao {

	
	private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final JdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
    }
	
	@Override
	public int count() {
		return jdbc.queryForObject("select count(*) from author", Integer.class);
	}	

	@Override
	public Long insert(Author author) {
		String sql = "insert into author (`name`, `nationality`) values (:name, :nationality)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("name", author.getName());
		paramSource.addValue("nationality", author.getNationality());
		KeyHolder keyHolder = new GeneratedKeyHolder( );
		String[] keyColumnNames = new String[]{"id"};
		namedParameterJdbcOperations.update(sql, paramSource, keyHolder, keyColumnNames );
		
		Number key = keyHolder.getKey();
		return key.longValue();
		
	}

	@Override
	public Author getById(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from author where id = :id", params, new AuthorMapper()
        );	
	}

	private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String nationality = resultSet.getString("nationality");
            
            return new Author(id, name, nationality);
        }
    }
	
	
	@Override
	public List<Author> getAll() {
		return jdbc.query("select * from author", new AuthorMapper());
	}

	@Override
	public void deleteById(Long id) {
		 Map<String, Object> params = Collections.singletonMap("id", id);
	        namedParameterJdbcOperations.update(
	                "delete from author where id = :id", params
	        );
		
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

}
