package pawkordek.comicviewer.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pawkordek.comicviewer.model.Tag;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static pawkordek.comicviewer.helper.provider.ExampleComicsProvider.tag1;
import static pawkordek.comicviewer.helper.provider.ExampleComicsProvider.tag2;

@ExtendWith(SpringExtension.class)
@JdbcTest
@ComponentScan
public class TagDAOTests {

    @Autowired
    TagDAO tagDAO;

    @Test
    public void ShouldReturn_thePreviouslyCreatedTags() {
        List<Tag> returnedTags = tagDAO.getAll();
        assertThat(returnedTags, hasItems(
                tag1, tag2)
        );
    }
}
