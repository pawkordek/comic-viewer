package pawkordek.comicviewer.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.service.ComicsService;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChaptersController {

    @NonNull
    private final ComicsService comicsService;

    @RequestMapping("/comics/{comicId}/chapters/{chapterId}/pages/{pageId}")
    ModelAndView page(@PathVariable("comicId") int comicId,
                      @PathVariable("chapterId") int chapterId,
                      @PathVariable("pageId") int pageId) {
        ModelAndView modelAndView = new ModelAndView("page");
        Comic comic = comicsService.getComic(comicId);
        modelAndView.addObject("comic", comic);
        modelAndView.addObject("chapterId", chapterId);
        modelAndView.addObject("pageId", pageId);
        return modelAndView;
    }
}
