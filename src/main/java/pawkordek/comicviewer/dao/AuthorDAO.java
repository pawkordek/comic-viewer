package pawkordek.comicviewer.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Author;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_AUTHOR_DATA_QUERY =
            "select a.id , a.first_name, a.middle_name, a.last_name, r.id as roles_id, r.name as roles_name " +
                    "from authors as a " +
                    "inner join author_author_role as ar " +
                    "on a.id = ar.author_id " +
                    "inner join author_roles as r " +
                    "on ar.author_role_id = r.id ";

    public void create(List<Author> authors) {
        List<Object[]> authorsToInsert = getAuthorsToInsertFrom(authors);
        insertAuthors(authorsToInsert);
        List<Integer> insertedAuthorsIds = getInsertedAuthorsIds(authorsToInsert);
        List<Object[]> authorsRolesToInsert = getAuthorsRolesToInsert(authors.iterator(), insertedAuthorsIds.iterator());
        insertAuthorsRolesForInsertedAuthors(authorsRolesToInsert);
    }

    private List<Object[]> getAuthorsToInsertFrom(List<Author> authors) {
        return authors.stream()
                .map(author -> new Object[]{
                        author.getFirstName(),
                        author.getMiddleName(),
                        author.getLastName()
                }).collect(Collectors.toList());
    }

    private void insertAuthors(List<Object[]> authorsToInsert) {
        jdbcTemplate.batchUpdate(
                "insert into authors (first_name, middle_name, last_name) values (?,?,?)",
                authorsToInsert);
    }

    private List<Integer> getInsertedAuthorsIds(List<Object[]> authorsToInsert) {
        return jdbcTemplate.query(
                "select id from authors order by id desc " +
                        "limit " + authorsToInsert.size(),
                (rs, rowNum) -> rs.getInt("id"));
    }

    private List<Object[]> getAuthorsRolesToInsert(Iterator<Author> authorsIterator, Iterator<Integer> authorIdsIterator) {
        List<Object[]> authorsRolesToInsert = new ArrayList<>();
        while (authorsIterator.hasNext() && authorIdsIterator.hasNext()) {
            List<Object[]> authorRoles = authorsIterator.next().getRoles().stream()
                    .map(role -> new Object[]{
                            authorIdsIterator.next(),
                            role.getId()
                    }).collect(Collectors.toList());
            authorsRolesToInsert.addAll(authorRoles);
        }
        return authorsRolesToInsert;
    }

    private void insertAuthorsRolesForInsertedAuthors(List<Object[]> authorsRolesToInsert) {
        jdbcTemplate.batchUpdate("insert into author_author_role (author_id, author_role_id) values (?, ?)", authorsRolesToInsert);
    }

    public List<Author> getAll() {
        return jdbcTemplate.query(
                SELECT_ALL_AUTHOR_DATA_QUERY,
                authorsExtractor);
    }

    private final ResultSetExtractor<List<Author>> authorsExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(Author.class);

    public List<Author> getAuthorsOfComicWithId(long comicId) {
        return jdbcTemplate.query(
                SELECT_ALL_AUTHOR_DATA_QUERY +
                        "inner join comic_data_author as c " +
                        "on a.id = c.author_id " +
                        "where c.comic_data_id = " + comicId, authorsExtractor);
    }
}
