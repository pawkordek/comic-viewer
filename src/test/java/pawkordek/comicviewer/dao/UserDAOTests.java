package pawkordek.comicviewer.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.User;

import static pawkordek.comicviewer.helper.ExampleUsersProvider.user;

@RunWith(SpringRunner.class)
@ComponentScan
@JdbcTest
public class UserDAOTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void ReturnedUser_shouldBeTheSame_asPreviouslyInitializedUser() {
        User returnedUser = userDAO.getUserWithName("user");
        Assert.assertEquals(user, returnedUser);
    }
}
