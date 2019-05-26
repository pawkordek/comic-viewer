package pawkordek.comicviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Author;

import java.util.List;
import java.util.stream.Collectors;

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
        return jdbcTemplate.query("select id, first_name, middle_name, last_name from authors",
                (resultSet, rowNum) -> new Author(resultSet));
    }
}
