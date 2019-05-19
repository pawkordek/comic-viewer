package pawkordek.comicviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pawkordek.comicviewer.dao.ComicDAO;
import pawkordek.comicviewer.model.Comic;

import java.util.List;

@Controller
public class ComicsController {

    @Autowired
    ComicDAO comicDAO;

    @RequestMapping("/comics")
    ModelAndView comics() {
        List<Comic> comics = comicDAO.getAll();
        ModelAndView modelAndView = new ModelAndView("comics");
        modelAndView.addObject("comics", comics);
        return modelAndView;
    }
}
