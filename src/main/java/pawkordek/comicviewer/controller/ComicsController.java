package pawkordek.comicviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/comic/{id}")
    ModelAndView comic(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("comic");
        Comic comic = comicsService.getComic(id);
        modelAndView.addObject("comic", comic);
        return modelAndView;
    }
}
