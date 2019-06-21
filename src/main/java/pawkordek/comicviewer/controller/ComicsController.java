package pawkordek.comicviewer.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.model.Tag;
import pawkordek.comicviewer.service.ComicsService;
import pawkordek.comicviewer.service.TagsService;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComicsController {

    @NonNull
    private final ComicsService comicsService;

    @NonNull
    private final TagsService tagsService;

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
        List<Comic> comics = comicsService.getAllComicsWithTitleOrAuthorNameLike(searchCriteria);
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

    @RequestMapping(value = "/comics-advanced-search", method = RequestMethod.GET)
    ModelAndView displayAdvancedSearch() {
        ModelAndView modelAndView = new ModelAndView("comics_advanced_search");
        List<Tag> tags = tagsService.getAllTags();
        modelAndView.addObject("availableTags", tags);
        return modelAndView;
    }

    @RequestMapping(value = "/comics-advanced-search", method = RequestMethod.POST)
    ModelAndView performAdvancedSearch(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String authorFirstName,
            @RequestParam(required = false) String authorMiddleName,
            @RequestParam(required = false) String authorLastName,
            @RequestParam(required = false) List<Integer> selectedTags
            ) {
        ModelAndView modelAndView = new ModelAndView("comics_advanced_search");
        List<Tag> tags = tagsService.getAllTags();
        modelAndView.addObject("availableTags", tags);
        List<Comic> comics = comicsService.getAllComicsWithParameters(
                title,
                authorFirstName,
                authorMiddleName,
                authorLastName,
                selectedTags
        );
        modelAndView.addObject("comics", comics);
        return modelAndView;
    }
}
