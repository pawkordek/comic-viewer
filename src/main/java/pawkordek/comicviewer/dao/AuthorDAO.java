package pawkordek.comicviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Author;
import pawkordek.comicviewer.model.AuthorRowMapper;

import java.util.List;
import java.util.stream.Collectors;

import static pawkordek.comicviewer.dao.helper.SQLFormatter.prepareInClause;

@Repository
public class AuthorDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(List<Author> authors) {
        List<Object[]> authorsToInsert = authors.stream()
                .map(author -> new Object[]{
                        author.getFirstName(),
                        author.getMiddleName(),
                        author.getLastName(),
                }).collect(Collectors.toList());
        jdbcTemplate.batchUpdate("insert into authors (first_name, middle_name, last_name) values (?,?,?)", authorsToInsert);
    }

    public List<Author> getAll() {
        return jdbcTemplate.query("select id, first_name, middle_name, last_name from authors", new AuthorRowMapper());
    }

    public List<Author> getAuthorsOfComicWithId(long id) {
        List<Integer> authorIds = jdbcTemplate.query(
                "select author_id from comic_data_author where comic_data_id = " + id,
                (rs, rowNum) -> rs.getInt("author_id"));
        return jdbcTemplate.query(
                "select id, first_name, middle_name, last_name from authors where id in (" + prepareInClause(authorIds) + ")",
                new AuthorRowMapper());
    }
}
