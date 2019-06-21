package pawkordek.comicviewer.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.ComicDAO;
import pawkordek.comicviewer.model.Comic;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComicsService {

    @NonNull
    private final ComicDAO comicDAO;

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
        List<Pair<String, Object>> searchCriteriaAttributes = new ArrayList<>();
        searchCriteriaAttributes.add(new ImmutablePair<>("title", searchCriteria));
        searchCriteriaAttributes.add(new ImmutablePair<>("author_first_name", searchCriteria));
        searchCriteriaAttributes.add(new ImmutablePair<>("author_middle_name", searchCriteria));
        searchCriteriaAttributes.add(new ImmutablePair<>("author_last_name", searchCriteria));
        return comicDAO.getAllWithAttributes(searchCriteriaAttributes);
    }

    public List<Comic> getAllComicsWithParameters(
            String title,
            String authorFirstName,
            String authorMiddleName,
            String authorLastName,
            List<Integer> tagsIds
    ) {
        List<Pair<String, Object>> searchCriteriaAttributes = new ArrayList<>();
        if (StringUtils.isNotBlank(title)) {
            searchCriteriaAttributes.add(new ImmutablePair<>("title", title));
        }
        if (StringUtils.isNotBlank(authorFirstName)) {
            searchCriteriaAttributes.add(new ImmutablePair<>("author_first_name", authorFirstName));
        }
        if (StringUtils.isNotBlank(authorMiddleName)) {
            searchCriteriaAttributes.add(new ImmutablePair<>("author_middle_name", authorMiddleName));
        }
        if (StringUtils.isNotBlank(authorLastName)) {
            searchCriteriaAttributes.add(new ImmutablePair<>("author_last_name", authorLastName));
        }
        if (tagsIds != null) {
            for (int tagId : tagsIds) {
                searchCriteriaAttributes.add(new ImmutablePair<>("tag_id", tagId));
            }
        }
        return comicDAO.getAllWithAttributes(searchCriteriaAttributes);
    }
}
