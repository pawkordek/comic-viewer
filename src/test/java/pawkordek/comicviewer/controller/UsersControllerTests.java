package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawkordek.comicviewer.helper.HeaderVerifier.expectLoggedInHeader;
import static pawkordek.comicviewer.helper.HeaderVerifier.expectNotLoggedInHeader;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void LoginView_ShouldHaveProperHeaderLinks_WhenNotLoggedIn() throws Exception {
        expectNotLoggedInHeader(mvc.perform(get("/login")));
    }

    @WithMockUser(roles = {"user"})
    @Test
    public void LoginView_ShouldHaveProperHeaderLinks_WhenLoggedIn() throws Exception {
        expectLoggedInHeader(mvc.perform(get("/login")));
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
}
