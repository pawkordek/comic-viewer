package pawkordek.comicviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.ComicDAO;
import pawkordek.comicviewer.model.Comic;

import java.util.List;
import java.util.Map;

@Service
public class ComicsService {

    @Autowired
    ComicDAO comicDAO;

    public List<Comic> getAllComics() {
        return comicDAO.getAll();
    }

    public Comic getComic(int id) {
        return comicDAO.getComic(id);
    }

    public List<Comic> getAllComicsWithAttributes(Map<String, Object> attributes) {
        return comicDAO.getAllWithAttributes(attributes);
    }
}
