package pawkordek.comicviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.TagDAO;
import pawkordek.comicviewer.model.Tag;

import java.util.List;

@Service
public class TagsService {

    @Autowired
    TagDAO tagDAO;

    public List<Tag> getAllTags() {
        return tagDAO.getAll();
    }
}
