package pawkordek.comicviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.Comic;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ComicDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(List<Comic> comics) {
        List<Object[]> comicsToInsert = comics.stream()
                .map(comic -> new Object[]{
                        comic.getTitle(),
                        comic.getPath()
                }).collect(Collectors.toList());
        jdbcTemplate.batchUpdate("insert into comics (title, path) values (?,?)", comicsToInsert);
    }


    public List<Comic> getAll() {
        return jdbcTemplate.query("select id, title, path from comics",
                (resultSet, rowNum) -> new Comic(resultSet));
    }
}

