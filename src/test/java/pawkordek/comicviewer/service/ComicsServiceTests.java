package pawkordek.comicviewer.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pawkordek.comicviewer.model.Comic;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pawkordek.comicviewer.helper.provider.ExampleComicsProvider.*;

@ExtendWith(SpringExtension.class)
@ComponentScan
@SpringBootTest
public class ComicsServiceTests {

    @Autowired
    private ComicsService comicsService;

    private static List<Comic> comics = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        comics.add(comic1);
        comics.add(comic2);
        comics.add(comic3);
    }

    @Test
    public void ReturnedComics_shouldContainComics_thatWereInitializedAtTheStart() {
        List<Comic> returnedComics = comicsService.getAllComics();
        assertThat(returnedComics, hasItems(
                comic1, comic2, comic3
        ));
    }

    @Test
    public void ReturnedComic_shouldContain_theSecondComic() {
        int id = 2;
        Comic returnedComic = comicsService.getComic(id);
        assertEquals(comic2, returnedComic);
    }

    @Test
    public void EmptyList_shouldBeReturned_whenNoComicsFound() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("This title doesn't exist");
        assertEquals(Collections.emptyList(), returnedComics);
    }

    @Test
    public void EmptyList_shouldBeReturned_whenSearchingWithEmptySearchCriteria() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("");
        assertEquals(Collections.emptyList(), returnedComics);

    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByTitleWhenItIsWrittenAsIs() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("Asterix");
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByTitleWithSmallCase() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("Asterix");
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByTitleWithBigCase() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("Asterix");
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByAuthorFirstName() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("Albert");
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByAuthorMiddleName() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("Jerzy");
        assertEquals(comic2, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByAuthorLastName() {
        List<Comic> returnedComics = comicsService.getAllComicsWithTitleOrAuthorNameLike("Christa");
        assertEquals(comic1, returnedComics.get(0));
    }
}
