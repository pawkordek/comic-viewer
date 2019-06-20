package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawkordek.comicviewer.helper.HeaderVerifier.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComicsControllerTests {
    private final String COMICS_SIMPLE_SEARCH_URL = "/comics-search-simple";

    @Autowired
    private MockMvc mvc;

    @Test
    public void ComicsUrl_ShouldMap_ToComicsView() throws Exception {
        mvc.perform(get("/comics"))
                .andExpect(status().isOk())
                .andExpect(view().name("comics"));
    }

    @Test
    public void ComicsView_ShouldHaveProperHeaderLinks_WhenNotLoggedIn() throws Exception {
        expectNotLoggedInHeader(mvc.perform(get("/comics")));
    }

    @WithMockUser(username = "user")
    @Test
    public void ComicsView_ShouldHaveProperHeaderLinks_WhenLoggedIn() throws Exception {
        expectLoggedInHeaderForUserCalledUser(mvc.perform(get("/comics")));
    }

    @Test
    public void ComicsView_ShouldHave_LinksToAllDisplayedComics() throws Exception {
        mvc.perform(get("/comics"))
                .andExpect(content().string(containsString("<a href=\"/comic/1\">Kajko i Kokosz</a>")))
                .andExpect(content().string(containsString("<a href=\"/comic/2\">Tytus, Romek i Atomek</a>")))
                .andExpect(content().string(containsString("<a href=\"/comic/3\">Asterix</a>")));
    }

    @Test
    public void ComicsView_ShouldHave_ComicsSearchForm() throws Exception {
        mvc.perform(get("/comics"))
                .andExpect(content().string(containsString("<form action=\"/comics-search-simple\" method=\"post\">")))
                .andExpect(content().string(containsString("Search comics by title or author: <input type=\"text\" value=\"\" name=\"searchCriteria\"/>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Search\"/>")));
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByTitleForIndependentlyOfCase() throws Exception {
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "Tytus").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "tytus").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "TYTUS").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByAuthorFirstName() throws Exception {
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "henryk").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "Henryk").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "HENRYK").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByAuthorMiddleName() throws Exception {
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "Jerzy").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "jerzy").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "JERZY").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByAuthorLastName() throws Exception {
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "Chmielewski").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "chmielewski").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
        mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param("searchCriteria", "CHMIELEWSKI").with(csrf()))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));
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

    @Test
    public void ComicView_ShouldHave_ComicCoverWithAltMessage() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(content().string(containsString("<img alt=\"Image not available\" src=\"/comics/kajko_kokosz/cover.jpg\"/>")));
    }

    @Test
    public void ComicView_ShouldHave_ListOfChapters() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(content().string(containsString("<a href=\"/comics/1/chapters/0/pages/1\">Chapter 1 - KK1</a>\n")))
                .andExpect(content().string(containsString("<a href=\"/comics/1/chapters/1/pages/1\">Chapter 2 - KK2</a>\n")));
    }
}
