package pawkordek.comicviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.ComicData;
import pawkordek.comicviewer.model.ComicDataRowMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ComicDataDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void create(List<ComicData> comicData) {
        List<Object[]> comicsToInsert = comicData.stream()
                .map(data -> new Object[]{
                        data.getTitle(),
                        data.getPath()
                }).collect(Collectors.toList());
        jdbcTemplate.batchUpdate("insert into comic_data (title, path) values (?,?)", comicsToInsert);
    }


    public List<ComicData> getAll() {
        return jdbcTemplate.query("select id, title, path from comic_data", new ComicDataRowMapper());
    }
}

