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
public class ComicsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void ComicsUrl_ShouldMap_ToComicsView() throws Exception {
        mvc.perform(get("/comics"))
                .andExpect(status().isOk())
                .andExpect(view().name("comics"));
    }

    @Test
    public void ComicsView_ShouldHave_AllHeaderLinks() throws Exception {
        mvc.perform(get("/comics"))
                .andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")));
    }

    @Test
    public void ComicsView_ShouldHave_LinksToAllDisplayedComics() throws Exception {
        mvc.perform(get("/comics"))
                .andExpect(content().string(containsString("<a href=\"/comic/1\">Kajko i Kokosz</a>")))
                .andExpect(content().string(containsString("<a href=\"/comic/2\">Tytus, Romek i Atomek</a>")))
                .andExpect(content().string(containsString("<a href=\"/comic/3\">Asterix</a>")));
    }

    @Test
    public void ComicUrl_ShouldMap_ToComicView() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comic"));
    }

    @Test
    public void ComicView_ShouldHave_AllHeaderLinks() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")));
    }

    @Test
    public void ComicView_ShouldHave_AllComicInformationDisplayed() throws Exception {
        mvc.perform(get("/comic/2"))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")))
                .andExpect(content().string(containsString("Polish")));
    }

}
