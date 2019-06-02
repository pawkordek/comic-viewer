package pawkordek.comicviewer.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.Comic;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static pawkordek.comicviewer.helper.ExampleComicsProvider.*;

@RunWith(SpringRunner.class)
@ComponentScan
@JdbcTest
public class ComicDAOTests {

    @Autowired
    private ComicDAO comicDAO;

    private List<Comic> comics = new ArrayList<>();

    @Before
    public void setUp() {
        comics.add(comic1);
        comics.add(comic2);
        comics.add(comic3);
    }

    @Test
    public void ReturnedComics_shouldContainComics_thatWereInitializedAtTheStart() {
        List<Comic> returnedComics = comicDAO.getAll();
        Assert.assertThat(returnedComics, hasItems(
                comic1, comic2, comic3
        ));
    }
}
