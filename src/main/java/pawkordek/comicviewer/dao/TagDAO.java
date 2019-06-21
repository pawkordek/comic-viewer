package pawkordek.comicviewer.dao;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Tag;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagDAO {

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    @NonNull
    private final RowMapper<Tag> tagRowMapper = JdbcTemplateMapperFactory.newInstance().newRowMapper(Tag.class);

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
