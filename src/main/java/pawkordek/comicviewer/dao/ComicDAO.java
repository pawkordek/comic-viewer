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
                "select c.id , c.title, c.path, " +
                        "a.id as authors_id, a.first_name as authors_first_name, a.middle_name as authors_middle_name, a.last_name as authors_last_name, " +
                        "r.id as authors_roles_id, r.name as authors_roles_name " +
                        "from comics as c " +
                        "inner join comic_author as ca " +
                        "on c.id = ca.comic_id " +
                        "inner join authors as a " +
                        "on ca.author_id = a.id " +
                        "inner join author_author_role as ar " +
                        "on a.id = ar.author_id " +
                        "inner join author_roles as r " +
                        "on ar.author_role_id = r.id " +
                        "order by c.id "
                , comicsExtractor);
    }

    private final ResultSetExtractor<List<Comic>> comicsExtractor =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .newResultSetExtractor(Comic.class);

}
