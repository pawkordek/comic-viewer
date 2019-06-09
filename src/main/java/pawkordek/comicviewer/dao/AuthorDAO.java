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
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_AUTHOR_DATA_QUERY =
            "       SELECT " +
                    "   a.id , a.first_name, a.middle_name, a.last_name, " +
                    "   r.id AS roles_id, r.name AS roles_name " +
                    "FROM " +
                    "   authors AS a " +
                    "   INNER JOIN author_author_role AS ar ON a.id = ar.author_id " +
                    "   INNER JOIN author_roles as r ON ar.author_role_id = r.id ";

    public void create(List<Author> authors) {
        List<Object[]> authorsToInsert = getAuthorsToInsertFrom(authors);
        insertAuthors(authorsToInsert);
        List<Integer> insertedAuthorsIds = getInsertedAuthorsIds(authorsToInsert);
        List<Object[]> rolesIdsPerAuthor = getRolesIdsForEveryInsertedAuthorid(authors.iterator(), insertedAuthorsIds.iterator());
        insertRolesIdsForInsertedAuthors(rolesIdsPerAuthor);
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
                "   INSERT INTO " +
                        "   authors (first_name, middle_name, last_name) " +
                        "VALUES " +
                        "   (?,?,?)",
                authorsToInsert);
    }

    private List<Integer> getInsertedAuthorsIds(List<Object[]> authorsToInsert) {
        return jdbcTemplate.query(
                "   SELECT " +
                        "   id " +
                        "FROM " +
                        "   authors " +
                        "ORDER BY" +
                        "   id DESC " +
                        "LIMIT " + authorsToInsert.size(),
                (rs, rowNum) -> rs.getInt("id"));
    }

    private List<Object[]> getRolesIdsForEveryInsertedAuthorid(Iterator<Author> authorsIterator, Iterator<Integer> authorIdsIterator) {
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

    private void insertRolesIdsForInsertedAuthors(List<Object[]> authorsRolesToInsert) {
        jdbcTemplate.batchUpdate(
                "   INSERT INTO " +
                        "   author_author_role (author_id, author_role_id) " +
                        "VALUES " +
                        "   (?, ?)",
                authorsRolesToInsert);
    }

    public List<Author> getAll() {
        return jdbcTemplate.query(
                SELECT_ALL_AUTHOR_DATA_QUERY,
                authorsExtractor);
    }

    private final ResultSetExtractor<List<Author>> authorsExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .newResultSetExtractor(Author.class);

    public List<Author> getAuthorsOfComicWithId(long comicId) {
        return jdbcTemplate.query(
                SELECT_ALL_AUTHOR_DATA_QUERY +
                        "INNER JOIN comic_data_author as c ON a.id = c.author_id " +
                        "WHERE " +
                        "   c.comic_data_id = " + comicId,
                authorsExtractor);
    }
}
