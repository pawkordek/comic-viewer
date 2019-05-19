package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void IndexView_ShouldHave_AllHeaderLinks() throws Exception {
        mvc.perform(get("/"))
                .andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")));
    }
}
