package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void ShouldRedirectToLoginPage_whenUserNotLoggedIn() throws Exception {
        mvc.perform(get("/user-profile"))
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    public void LoginUrl_shouldMap_toLoginView() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }
}
