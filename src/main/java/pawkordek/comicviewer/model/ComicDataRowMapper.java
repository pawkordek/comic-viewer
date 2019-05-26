package pawkordek.comicviewer.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComicDataRowMapper implements RowMapper<ComicData> {
    @Override
    public ComicData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return ComicData.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .path(resultSet.getString("path"))
                .build();
    }
}
