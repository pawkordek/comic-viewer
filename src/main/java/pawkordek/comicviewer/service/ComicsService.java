package pawkordek.comicviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.AuthorDAO;
import pawkordek.comicviewer.dao.ComicDataDAO;
import pawkordek.comicviewer.model.Author;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.model.ComicData;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicsService {

    @Autowired
    ComicDataDAO comicDataDAO;

    @Autowired
    AuthorDAO authorDAO;

    public List<Comic> getAllComics() {
        List<Comic> comics = new ArrayList<>();
        List<ComicData> comicsData = comicDataDAO.getAll();
        for (ComicData comicData : comicsData) {
            List<Author> authors = authorDAO.getAuthorsOfComicWithId(comicData.getId());
            comics.add(Comic.builder()
                    .data(comicData)
                    .authors(authors)
                    .build());
        }
        return comics;
    }
}
