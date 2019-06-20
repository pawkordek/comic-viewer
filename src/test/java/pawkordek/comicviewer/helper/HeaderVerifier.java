package pawkordek.comicviewer.helper;

import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class HeaderVerifier {

    public static ResultActions expectHeaderForNotLoggedInUser(ResultActions resultActions) throws Exception {
        return andExpectSimpleSearchForm(resultActions)
                .andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")))
                .andExpect(content().string(containsString("<a href=\"/login\">LOGIN</a>")));
    }

    public static ResultActions expectHeaderForLoggedInUserCalledUser(ResultActions resultActions) throws Exception {
        return andExpectSimpleSearchForm(resultActions)
                .andExpect(content().string(containsString("<a href=\"/\">HOME</a>")))
                .andExpect(content().string(containsString("<a href=\"/comics\">ALL COMICS</a>")))
                .andExpect(content().string(containsString("<a href=\"/user-profile\">USER&#39;S PROFILE</a>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Logout\">")));
    }

    private static ResultActions andExpectSimpleSearchForm(ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(content().string(containsString("<form action=\"/comics-search-simple\" method=\"post\">")))
                .andExpect(content().string(containsString("<input type=\"text\" placeholder=\"Search comics by title or author:\" value=\"\" name=\"searchCriteria\"/>")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Search\"/>")));
    }
}
