package pawkordek.comicviewer.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Tag;

import java.util.List;

@Repository
public class TagDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Tag> tagRowMapper = JdbcTemplateMapperFactory.newInstance().newRowMapper(Tag.class);

    @Autowired
    public TagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> getAll() {
        return jdbcTemplate.query(
                "   SELECT " +
                        "   id, name " +
                        "FROM " +
                        "   tags ",
                tagRowMapper
        );
    }
}
