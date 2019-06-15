package pawkordek.comicviewer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChaptersControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void ChapterPageUrl_shouldMap_toPageView() throws Exception {
        mvc.perform(get("/comics/1/chapters/1/pages/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("page"));
    }

    @Test
    public void ChapterPageView_shouldHaveCertainElements() throws Exception {
        mvc.perform(get("/comics/1/chapters/1/pages/1"))
                .andExpect(content().string(containsString("<title>Kajko i Kokosz - KK1</title>\n")))
                .andExpect(content().string(containsString("<img alt=\"Image not available\" src=\"/comics/kajko_kokosz/kk1/1.jpg\"/>")));
    }
}
