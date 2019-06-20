package pawkordek.comicviewer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pawkordek.comicviewer.model.User;
import pawkordek.comicviewer.model.UserAuthenticationDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pawkordek.comicviewer.helper.provider.ExampleUsersProvider.user;

@ExtendWith(SpringExtension.class)
@ComponentScan
@SpringBootTest
public class UsersServiceTests {

    @Autowired
    private UsersService usersService;

    private final UserDetails userDetails = new UserAuthenticationDetails(user);

    @Test
    public void ReturnedUser_shouldBe_theSameAsPreviouslyInitializedUser() {
        User returnedUser = usersService.getUserWithName("user");
        assertEquals(user, returnedUser);
    }

    @Test
    public void ReturnedUserAuthenticationDetails_shouldBe_theSameAsPreviouslyInitialized() {
        UserDetails returnedUserDetails = usersService.loadUserByUsername("user");
        assertEquals(returnedUserDetails, userDetails);
    }
}
