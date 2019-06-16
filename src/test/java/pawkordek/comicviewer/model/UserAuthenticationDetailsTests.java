package pawkordek.comicviewer.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import static pawkordek.comicviewer.helper.ExampleUsersProvider.admin;
import static pawkordek.comicviewer.helper.ExampleUsersProvider.user;

public class UserAuthenticationDetailsTests {
    private final UserDetails userDetails = new UserAuthenticationDetails(user);

    @Test
    public void ReturnedAuthority_shouldBe_User() {
        GrantedAuthority authority = userDetails.getAuthorities().stream().findFirst().get();
        assertEquals("user", authority.getAuthority());
    }

    @Test
    public void ReturnedPassword_shouldBe_UsersPassword() {
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void ReturnedUsername_shouldBe_UsersLogin() {
        assertEquals(user.getLogin(), userDetails.getUsername());
    }

    @Test
    public void BooleanOverrides_shouldAll_returnTrue() {
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void AuthenticationDetails_shouldBeNotEqual_forTwoDifferentUsers() {
        UserDetails adminDetails = new UserAuthenticationDetails(admin);
        assertNotEquals(adminDetails, userDetails);
    }
}
