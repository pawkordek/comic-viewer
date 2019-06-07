package pawkordek.comicviewer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.Comic;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static pawkordek.comicviewer.helper.ExampleComicsProvider.*;

@RunWith(SpringRunner.class)
@ComponentScan
@SpringBootTest
public class ComicsServiceTests {

    @Autowired
    private ComicsService comicsService;

    private List<Comic> comics = new ArrayList<>();

    @Before
    public void setUp() {
        comics.add(comic1);
        comics.add(comic2);
        comics.add(comic3);
    }

    @Test
    public void ReturnedComics_shouldContainComics_thatWereInitializedAtTheStart() {
        List<Comic> returnedComics = comicsService.getAllComics();
        Assert.assertThat(returnedComics, hasItems(
                comic1, comic2, comic3
        ));
    }

    @Test
    public void ReturnedComic_shouldContain_theSecondComic() {
        int id = 2;
        Comic returnedComic = comicsService.getComic(id);
        assertEquals(comic2, returnedComic);
    }
}
