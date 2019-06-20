package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawkordek.comicviewer.helper.ComicVerifier.expectThatCorrectComicDataIsDisplayed;
import static pawkordek.comicviewer.helper.HeaderVerifier.expectHeaderForLoggedInUserCalledUser;
import static pawkordek.comicviewer.helper.HeaderVerifier.expectHeaderForNotLoggedInUser;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComicsControllerTests {
    private final String COMICS_SIMPLE_SEARCH_URL = "/comics-search-simple";
    private final String COMICS_SIMPLE_SEARCH_PARAM = "searchCriteria";
    private final String COMICS_URL = "/comics";

    @Autowired
    private MockMvc mvc;

    @Test
    public void ComicsUrl_shouldMap_toComicsView() throws Exception {
        mvc.perform(get(COMICS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("comics"));
    }

    @Test
    public void ComicsView_shouldHaveProperHeaderLinks_whenNotLoggedIn() throws Exception {
        expectHeaderForNotLoggedInUser(
                mvc.perform(get(COMICS_URL))
        );
    }

    @WithMockUser(username = "user")
    @Test
    public void ComicsView_shouldHaveProperHeaderLinks_whenLoggedIn() throws Exception {
        expectHeaderForLoggedInUserCalledUser(
                mvc.perform(get(COMICS_URL))
        );
    }

    @Test
    public void ComicsView_shouldHave_linksToAllDisplayedComics() throws Exception {
        mvc.perform(get(COMICS_URL))
                .andExpect(content().string(containsString("<a href=\"/comic/1\">Kajko i Kokosz</a>")))
                .andExpect(content().string(containsString("<a href=\"/comic/2\">Tytus, Romek i Atomek</a>")))
                .andExpect(content().string(containsString("<a href=\"/comic/3\">Asterix</a>")));
    }

    @Test
    public void ComicsView_shouldHave_comicsSearchForm() throws Exception {
        mvc.perform(get(COMICS_URL))
                .andExpect(content().string(containsString("<form action=\"/comics-search-simple\" method=\"post\">")))
                .andExpect(content().string(containsString("Search comics by title or author: <input type=\"text\" value=\"\" name=\"searchCriteria\"/>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Search\"/>")));
    }

    @Test
    public void ComicsView_shouldHaveDataOfComicsThatHaveBeenSearchedByTitle_independentlyOfWordsCase() throws Exception {
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Tytus")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("tytus")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("TYTUS")
        );
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByAuthorFirstName() throws Exception {
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("henryk")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Henryk")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("HENRYK")
        );
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByAuthorMiddleName() throws Exception {
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Jerzy")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("jerzy")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("JERZY")
        );
    }

    @Test
    public void ComicsView_ShouldHave_DataOfComicsThatHaveBeenSearchedByAuthorLastName() throws Exception {
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Chmielewski")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("chmielewski")
        );
        expectThatCorrectComicDataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("CHMIELEWSKI")
        );
    }

    private ResultActions performSimpleComicsSearchWithSearchCriteria(String searchCriteria) throws Exception {
        return mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param(COMICS_SIMPLE_SEARCH_PARAM, searchCriteria).with(csrf()));
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
