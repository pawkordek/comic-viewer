package pawkordek.comicviewer.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Comic;

import java.util.List;


@Repository
public class ComicDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Comic> getAll() {
        return jdbcTemplate.query(
                "   SELECT " +
                        "   c.id , c.title, c.path, " +
                        "   a.id AS authors_id, a.first_name AS authors_first_name, " +
                        "   a.middle_name AS authors_middle_name, a.last_name AS authors_last_name, " +
                        "   r.id AS authors_roles_id, r.name AS authors_roles_name, " +
                        "   t.id AS tags_id, t.name AS tags_name " +
                        "FROM " +
                        "   comics AS c " +
                        "   INNER JOIN comic_author AS ca ON c.id = ca.comic_id " +
                        "   INNER JOIN authors AS a ON ca.author_id = a.id " +
                        "   INNER JOIN author_author_role AS ar ON a.id = ar.author_id " +
                        "   INNER JOIN author_roles AS r ON ar.author_role_id = r.id " +
                        "   INNER JOIN comic_tag AS ct ON c.id = ct.comic_id " +
                        "   INNER JOIN tags AS t ON t.id = ct.tag_id " +
                        "ORDER BY " +
                        "   c.id "
                , comicsExtractor);
    }

    private final ResultSetExtractor<List<Comic>> comicsExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .newResultSetExtractor(Comic.class);

}
