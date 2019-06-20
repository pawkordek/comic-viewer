package pawkordek.comicviewer.helper;

import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ComicVerifier {

    public static ResultActions expectThatCorrectComicDataIsDisplayed(ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(content().string(containsString("Tytus, Romek i Atomek")))
                .andExpect(content().string(containsString("Henryk Jerzy Chmielewski")));

    }
}
