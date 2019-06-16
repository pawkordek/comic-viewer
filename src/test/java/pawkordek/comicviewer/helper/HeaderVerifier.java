package pawkordek.comicviewer.helper;

import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class HeaderVerifier {

    public static ResultActions expectNotLoggedInHeader(ResultActions resultActions) throws Exception {
        return resultActions.andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")))
                .andExpect(content().string(containsString("<a href=\"/login\">LOGIN</a>")));
    }

    public static ResultActions expectLoggedInHeaderForUserCalledUser(ResultActions resultActions) throws Exception{
        return resultActions.andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")))
                .andExpect(content().string(containsString("<a href=\"/user-profile\">USER&#39;S PROFILE</a>")));
    }
}
