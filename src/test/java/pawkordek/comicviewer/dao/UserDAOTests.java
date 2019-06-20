package pawkordek.comicviewer.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pawkordek.comicviewer.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pawkordek.comicviewer.helper.provider.ExampleUsersProvider.user;

@ExtendWith(SpringExtension.class)
@ComponentScan
@JdbcTest
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void ReturnedUser_shouldBeTheSame_asPreviouslyInitializedUser() {
        User returnedUser = userDAO.getUserWithName("user");
        assertEquals(user, returnedUser);
    }
}
