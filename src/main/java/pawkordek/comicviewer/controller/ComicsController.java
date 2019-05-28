package pawkordek.comicviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.service.ComicsService;

import java.util.List;

@Controller
public class ComicsController {

    @Autowired
    ComicsService comicsService;

    @RequestMapping("/comics")
    ModelAndView comics() {
        ModelAndView modelAndView = new ModelAndView("comics");
        List<Comic> comics = comicsService.getAllComics();
        modelAndView.addObject("comics", comics);
        return modelAndView;
    }
}
