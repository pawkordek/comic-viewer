package pawkordek.comicviewer.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.*;

import javax.validation.constraints.Positive;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComicDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Comic> getAll() {
        List<ComicsTableRow> comicsTable = getComicsTable();
        return getComicsFrom(comicsTable);
    }

    private List<ComicsTableRow> getComicsTable() {
        return jdbcTemplate.query("select comic_data_id, author_id from comics",
                (resultSet, rowNum) -> new ComicsTableRow(resultSet));
    }

    private List<Comic> getComicsFrom(List<ComicsTableRow> comicsTable) {
        List<Comic> comics = new ArrayList<>();
        for (ComicsTableRow row : comicsTable) {
            ComicData comicData = findComicData(row.getComic_data_id());
            Author author = findAuthor(row.getAuthor_id());
            comics.add(
                    Comic.builder()
                            .data(comicData)
                            .author(author)
                            .build()
            );
        }
        return comics;
    }

    private ComicData findComicData(int comicDataId) {
        return jdbcTemplate.queryForObject(
                "select id, title, path from comic_data where id = " + comicDataId, new ComicDataRowMapper()
        );
    }

    private Author findAuthor(int authorId) {
        return jdbcTemplate.queryForObject(
                "select id, first_name, middle_name, last_name from authors where id = " + authorId, new AuthorRowMapper());
    }

    @Data
    private class ComicsTableRow {
        @Positive
        @Setter(AccessLevel.NONE)
        private final int comic_data_id;

        @Positive
        @Setter(AccessLevel.NONE)
        private final int author_id;

        ComicsTableRow(ResultSet resultSet) throws SQLException {
            this.comic_data_id = resultSet.getInt("comic_data_id");
            this.author_id = resultSet.getInt(("author_id"));
        }
    }
}

