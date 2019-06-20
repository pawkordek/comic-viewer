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

import java.util.List;

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

    @RequestMapping(value = "/comics-search-simple", method = RequestMethod.POST)
    ModelAndView performSimpleComicsSearch(@RequestParam String searchCriteria) {
        ModelAndView modelAndView = new ModelAndView("comics");
        List<Comic> comics = comicsService.getAllComicsWithTitleOrAuthorName(searchCriteria);
        modelAndView.addObject("comics", comics);
        return modelAndView;
    }

    @RequestMapping("/comic/{id}")
    ModelAndView comic(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("comic");
        Comic comic = comicsService.getComic(id);
        modelAndView.addObject("comic", comic);
        return modelAndView;
    }

    @RequestMapping("/comics-advanced-search")
    String displayAdvancedSearch(){
        return "comics_advanced_search";
    }
}
