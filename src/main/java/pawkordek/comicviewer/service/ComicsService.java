package pawkordek.comicviewer.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.ComicDAO;
import pawkordek.comicviewer.model.Comic;

import java.util.Collections;
import java.util.HashMap;
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

    public List<Comic> getAllComicsWithTitleOrAuthorNameLike(String searchCriteria) {
        if (StringUtils.isBlank(searchCriteria)) {
            return Collections.emptyList();
        } else {
            return getAllComicsWithSearchCriteria(searchCriteria);
        }
    }

    private List<Comic> getAllComicsWithSearchCriteria(String searchCriteria) {
        Map<String, Object> searchCriteriaAttributes = new HashMap<>();
        searchCriteriaAttributes.put("title", searchCriteria);
        searchCriteriaAttributes.put("author_first_name", searchCriteria);
        searchCriteriaAttributes.put("author_middle_name", searchCriteria);
        searchCriteriaAttributes.put("author_last_name", searchCriteria);
        return comicDAO.getAllWithAttributes(searchCriteriaAttributes);
    }
}
