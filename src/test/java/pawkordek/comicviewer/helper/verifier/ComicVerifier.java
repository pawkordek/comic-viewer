package pawkordek.comicviewer.helper.verifier;

import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ComicVerifier {
    public static ResultActions expectThatComic1DataIsDisplayed(ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(content().string(containsString("Kajko i Kokosz")))
                .andExpect(content().string(containsString("Janusz  Christa")));

    }

    public static ResultActions expectThatComic2DataIsDisplayed(ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));

    }

    public static ResultActions expectThatComic3DataIsDisplayed(ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(content().string(containsString("Asterix")))
                .andExpect(content().string(containsString("René  Goscinny")))
                .andExpect(content().string(containsString("Albert  Uderzo")));

    }

    public static ResultActions expectThatAllComicsDataIsDisplay(ResultActions resultActions) throws Exception {
        return expectThatComic1DataIsDisplayed(
                expectThatComic2DataIsDisplayed(
                        expectThatComic3DataIsDisplayed(resultActions)));
    }

}
