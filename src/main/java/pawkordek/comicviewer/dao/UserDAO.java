package pawkordek.comicviewer.dao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import pawkordek.comicviewer.model.User;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = JdbcTemplateMapperFactory.newInstance().newRowMapper(User.class);

    public User getUserWithName(String name) {
        List<User> users = jdbcTemplate.query(
                "   SELECT " +
                        "   id, name, login, pass, type " +
                        "FROM " +
                        "   users " +
                        "WHERE " +
                        "   name = ? "
                ,
                preparedStatement -> preparedStatement.setString(1, name),
                userRowMapper);
        return users.get(0);
    }
}
