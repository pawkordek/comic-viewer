package pawkordek.comicviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pawkordek.comicviewer.service.UsersService;

import java.util.Collections;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    @RequestMapping("/user-profile")
    public String userProfile() {
        return "user_profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLogin(@RequestParam(required = false) String registeredUser) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("errors", Collections.emptyList());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerNewUser(
            @RequestParam String username,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String passwordConfirm) {
        List<String> errors = usersService.getErrorsInUserData(username, login, password, passwordConfirm);
        if (errors.isEmpty()) {
            usersService.addNewUser(username, login, password);
            return new ModelAndView("redirect:/login?registeredUser=" + username);
        } else {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
    }
}

