package pawkordek.comicviewer.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawkordek.comicviewer.helper.verifier.ComicVerifier.expectThatAllComicsDataIsDisplay;
import static pawkordek.comicviewer.helper.verifier.ComicVerifier.expectThatComic2DataIsDisplayed;
import static pawkordek.comicviewer.helper.verifier.HeaderVerifier.expectHeaderForLoggedInUserCalledUser;
import static pawkordek.comicviewer.helper.verifier.HeaderVerifier.expectHeaderForNotLoggedInUser;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ComicsControllerTests {
    private final String COMICS_SIMPLE_SEARCH_URL = "/comics-search-simple";
    private final String COMICS_SIMPLE_SEARCH_PARAM = "searchCriteria";
    private final String COMICS_URL = "/comics";
    private final String ADVANCED_COMICS_SEARCH_URL = "/comics-advanced-search";

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
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Tytus")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("tytus")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("TYTUS")
        );
    }

    @Test
    public void ComicsView_shouldHave_dataOfComicsThatHaveBeenSearchedByAuthorFirstName() throws Exception {
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("henryk")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Henryk")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("HENRYK")
        );
    }

    @Test
    public void ComicsView_shouldHave_dataOfComicsThatHaveBeenSearchedByAuthorMiddleName() throws Exception {
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Jerzy")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("jerzy")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("JERZY")
        );
    }

    @Test
    public void ComicsView_shouldHave_dataOfComicsThatHaveBeenSearchedByAuthorLastName() throws Exception {
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("Chmielewski")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("chmielewski")
        );
        expectThatComic2DataIsDisplayed(
                performSimpleComicsSearchWithSearchCriteria("CHMIELEWSKI")
        );
    }

    private ResultActions performSimpleComicsSearchWithSearchCriteria(String searchCriteria) throws Exception {
        return mvc.perform(post(COMICS_SIMPLE_SEARCH_URL).param(COMICS_SIMPLE_SEARCH_PARAM, searchCriteria).with(csrf()));
    }

    @Test
    public void ComicUrl_shouldMap_toComicView() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comic"));
    }

    @Test
    public void ComicView_shouldHave_allHeaderLinks() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")));
    }

    @Test
    public void ComicView_shouldHave_allComicInformationDisplayed() throws Exception {
        mvc.perform(get("/comic/2"))
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")))
                .andExpect(content().string(containsString("Polish")));
    }

    @Test
    public void ComicView_shouldHave_comicCoverWithAltMessage() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(content().string(containsString("<img alt=\"Image not available\" src=\"/comics/kajko_kokosz/cover.jpg\"/>")));
    }

    @Test
    public void ComicView_shouldHave_listOfChapters() throws Exception {
        mvc.perform(get("/comic/1"))
                .andExpect(content().string(containsString("<a href=\"/comics/1/chapters/0/pages/1\">Chapter 1 - KK1</a>\n")))
                .andExpect(content().string(containsString("<a href=\"/comics/1/chapters/1/pages/1\">Chapter 2 - KK2</a>\n")));
    }

    @Test
    public void AdvancedComicsSearchUrl_shouldMap_toAdvancedComicsSearchView() throws Exception {
        mvc.perform(get(ADVANCED_COMICS_SEARCH_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("comics_advanced_search"));
    }

    @Test
    public void AdvancedComicsSearchView_shouldHaveProperHeaderLinks_whenNotLoggedIn() throws Exception {
        expectHeaderForNotLoggedInUser(
                mvc.perform(get(ADVANCED_COMICS_SEARCH_URL))
        );
    }

    @WithMockUser(username = "user")
    @Test
    public void AdvancedComicsSearchView_shouldHaveProperHeaderLinks_whenLoggedIn() throws Exception {
        expectHeaderForLoggedInUserCalledUser(
                mvc.perform(get(ADVANCED_COMICS_SEARCH_URL))
        );
    }

    @Test
    public void AdvancedComicsSearchView_shouldContain_searchForm() throws Exception {
        mvc.perform(get(ADVANCED_COMICS_SEARCH_URL))
                .andExpect(content().string(containsString("<form action=\"/comics-advanced-search\" method=\"post\">")))
                .andExpect(content().string(containsString("Search comics by:")))
                .andExpect(content().string(containsString("Title: <input type=\"text\" placeholder=\"title\" value=\"\" name=\"title\"/>")))
                .andExpect(content().string(containsString("Author first name: <input type=\"text\" placeholder=\"author first name\" value=\"\"\n" +
                        "                                  name=\"authorFirstName\"/>")))
                .andExpect(content().string(containsString("Author middle name: <input type=\"text\" placeholder=\"author middle name\" value=\"\"\n" +
                        "                                   name=\"authorMiddleName\"/>")))
                .andExpect(content().string(containsString("Author last name: <input type=\"text\" placeholder=\"author last name\" value=\"\"\n" +
                        "                                 name=\"authorLastName\"/>")))
                .andExpect(content().string(containsString("Tags:")))
                .andExpect(content().string(containsString("<select id=\"selectedTags\" name=\"selectedTags\" multiple=\"multiple\">")))
                .andExpect(content().string(containsString("<option value=\"1\">Polish</option>")))
                .andExpect(content().string(containsString("<option value=\"2\">French</option>")))
                .andExpect(content().string(containsString("</select>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Search\"/>")));
    }

    @Test
    public void AdvancedComicSearchView_shouldHaveCorrectComic_whenSearchingByTitle() throws Exception {
        expectThatComic2DataIsDisplayed(
                mvc.perform(post(ADVANCED_COMICS_SEARCH_URL).param("title", "Tytus").with(csrf()))
        );
    }

    @Test
    public void AdvancedComicSearchView_shouldHaveCorrectComic_whenSearchingByAuthorFirstName() throws Exception {
        expectThatComic2DataIsDisplayed(
                mvc.perform(post(ADVANCED_COMICS_SEARCH_URL).param("authorFirstName", "Henryk").with(csrf()))
        );
    }

    @Test
    public void AdvancedComicSearchView_shouldHaveCorrectComic_whenSearchingByAuthorMiddleName() throws Exception {
        expectThatComic2DataIsDisplayed(
                mvc.perform(post(ADVANCED_COMICS_SEARCH_URL).param("authorMiddleName", "Jerzy").with(csrf()))
        );
    }

    @Test
    public void AdvancedComicSearchView_shouldHaveCorrectComic_whenSearchingByAuthorLastName() throws Exception {
        expectThatComic2DataIsDisplayed(
                mvc.perform(post(ADVANCED_COMICS_SEARCH_URL).param("authorLastName", "Chmielewski").with(csrf()))
        );
    }

    @Test
    public void AdvancedComicSearchView_shouldHaveCorrectComics_whenSearchingByTag() throws Exception {
        expectThatAllComicsDataIsDisplay(
                mvc.perform(post(ADVANCED_COMICS_SEARCH_URL).param("selectedTags", "1,2").with(csrf()))
        );
    }
}
