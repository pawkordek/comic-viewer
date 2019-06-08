package pawkordek.comicviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.service.ComicsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ComicsController {

    @Autowired
    ComicsService comicsService;

    @RequestMapping(value = "/comics", method = RequestMethod.GET)
    ModelAndView comicsGET() {
        ModelAndView modelAndView = new ModelAndView("comics");
        List<Comic> comics = comicsService.getAllComics();
        modelAndView.addObject("comics", comics);
        return modelAndView;
    }

    @RequestMapping(value = "/comics", method = RequestMethod.POST)
    ModelAndView comicsPOST(@RequestParam String title){
        ModelAndView modelAndView = new ModelAndView("comics");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("title", title);
        List<Comic> comics = comicsService.getAllComicsWithAttributes(attributes);
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
