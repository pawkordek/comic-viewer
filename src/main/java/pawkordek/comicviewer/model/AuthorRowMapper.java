package pawkordek.comicviewer.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Author.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .middleName(resultSet.getString("middle_name"))
                .lastName(resultSet.getString("last_name"))
                .build();
    }
}
