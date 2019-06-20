package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawkordek.comicviewer.helper.HeaderVerifier.expectHeaderForLoggedInUserCalledUser;
import static pawkordek.comicviewer.helper.HeaderVerifier.expectHeaderForNotLoggedInUser;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void LoginView_ShouldHaveProperHeaderLinks_WhenNotLoggedIn() throws Exception {
        expectHeaderForNotLoggedInUser(mvc.perform(get("/login")));
    }

    @WithMockUser(username = "user")
    @Test
    public void LoginView_ShouldHaveProperHeaderLinks_WhenLoggedIn() throws Exception {
        expectHeaderForLoggedInUserCalledUser(mvc.perform(get("/login")));
    }

    @Test
    public void ShouldRedirectToLoginPage_whenUserNotLoggedIn() throws Exception {
        mvc.perform(get("/user-profile"))
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    public void LoginWithCorrectData_should_work() throws Exception {
        RequestBuilder loginRequest = post("/login").with(csrf())
                .param("username", "user")
                .param("password", "user");

        mvc.perform(loginRequest)
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void LoginWithIncorrectData_should_notWork() throws Exception {
        RequestBuilder loginRequest = post("/login")
                .param("username", "not a correct user name")
                .param("password", "not a correct password");

        mvc.perform(loginRequest)
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = {"user"})
    @Test
    public void ShouldShow_UserProfile_whenLoggedIn() throws Exception {
        mvc.perform(get("/user-profile"))
                .andExpect(view().name("user_profile"));
    }

    @Test
    public void LoginUrl_shouldMap_toLoginView() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void LoginView_ShouldHave_LoginForm() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(content().string(containsString("<form action=\"/login\" method=\"post\">")))
                .andExpect(content().string(containsString("Login: <input type=\"text\" value=\"\" name=\"username\"/>")))
                .andExpect(content().string(containsString("Password: <input type=\"password\" value=\"\" name=\"password\"/>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Login\"/>")));
    }

    @Test
    public void LoginView_shouldDisplayWelcomeMessage_IfPassedInARegisteredUsername() throws Exception {
        mvc.perform(get("/login?registeredUser=user"))
                .andExpect(content().string(containsString("Welcome to Comic Reader, USER!")));
    }

    @Test
    public void RegisterView_shouldHave_registerForm() throws Exception {
        mvc.perform(get("/register"))
                .andExpect(content().string(containsString("<form action=\"/register\" method=\"post\">")))
                .andExpect(content().string(containsString("Username: <input type=\"text\" value=\"\" name=\"username\"/>")))
                .andExpect(content().string(containsString("Login: <input type=\"text\" value=\"\" name=\"login\"/>")))
                .andExpect(content().string(containsString("Password: <input type=\"password\" value=\"\" name=\"password\"/>")))
                .andExpect(content().string(containsString("Confirm password: <input type=\"password\" value=\"\" name=\"passwordConfirm\"/>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Register\"/>")));
    }

    @Test
    public void RegisterView_whenRegisteredWithIncorrectData_shouldHaveErrorMessages() throws Exception {
        RequestBuilder registerRequest = post("/register").with(csrf())
                .param("username", "")
                .param("login", "")
                .param("password", "")
                .param("passwordConfirm", "");

        mvc.perform(registerRequest)
                .andExpect(content().string(containsString("Invalid username.")))
                .andExpect(content().string(containsString("Invalid login.")))
                .andExpect(content().string(containsString("Invalid password.")))
                .andExpect(content().string(containsString("Invalid password confirmation.")));
    }

    @Test
    public void RegisterView_whenRegisteredWithPasswordAndPasswordConfirmNotBeingTheSame_shouldHaveErrorMessage() throws Exception {
        RequestBuilder registerRequest = post("/register").with(csrf())
                .param("username", "some name")
                .param("login", "some login")
                .param("password", "first password")
                .param("passwordConfirm", "second password");

        mvc.perform(registerRequest)
                .andExpect(content().string(containsString("Passwords do not match.")));
    }

    @Test
    public void WhenRegisteringSucceeds_shouldRedirectToLoginView_withWelcomeMessage() throws Exception {
        RequestBuilder registerRequest = post("/register").with(csrf())
                .param("username", "user")
                .param("login", "login")
                .param("password", "password")
                .param("passwordConfirm", "password");

        mvc.perform(registerRequest)
                .andExpect(redirectedUrl("/login?registeredUser=user"));
    }
}
