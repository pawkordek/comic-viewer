package pawkordek.comicviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    @RequestMapping("/user-profile")
    public ModelAndView userProfile(){
        ModelAndView modelAndView = new ModelAndView("user-profile");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}
