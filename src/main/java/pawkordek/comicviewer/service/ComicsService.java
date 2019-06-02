package pawkordek.comicviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.ComicDAO;
import pawkordek.comicviewer.model.Comic;

import java.util.List;

@Service
public class ComicsService {

    @Autowired
    ComicDAO comicDAO;

    public List<Comic> getAllComics() {
        return comicDAO.getAll();
    }
}
