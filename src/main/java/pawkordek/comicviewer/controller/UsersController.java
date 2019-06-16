package pawkordek.comicviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pawkordek.comicviewer.service.UsersService;

@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    @RequestMapping("/user-profile")
    public String userProfile() {
        return "user_profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
