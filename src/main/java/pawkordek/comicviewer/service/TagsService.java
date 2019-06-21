package pawkordek.comicviewer.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.TagDAO;
import pawkordek.comicviewer.model.Tag;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagsService {

    @NonNull
    private final TagDAO tagDAO;

    public List<Tag> getAllTags() {
        return tagDAO.getAll();
    }
}
