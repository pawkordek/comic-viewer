package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static pawkordek.comicviewer.helper.verifier.HeaderVerifier.expectHeaderForLoggedInUserCalledUser;
import static pawkordek.comicviewer.helper.verifier.HeaderVerifier.expectHeaderForNotLoggedInUser;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void IndexUrl_ShouldMap_ToIndexView() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void IndexView_ShouldHaveProperHeaderLinks_WhenNotLoggedIn() throws Exception {
        expectHeaderForNotLoggedInUser(mvc.perform(get("/")));
    }

    @WithMockUser(username = "user")
    @Test
    public void IndexView_ShouldHaveProperHeaderLinks_WhenLoggedIn() throws Exception {
        expectHeaderForLoggedInUserCalledUser(mvc.perform(get("/")));
    }
}
