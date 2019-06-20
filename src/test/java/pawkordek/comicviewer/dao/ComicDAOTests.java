package pawkordek.comicviewer.dao;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
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
@JdbcTest
public class ComicDAOTests {

    @Autowired
    private ComicDAO comicDAO;

    private static List<Comic> comics = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        comics.add(comic1);
        comics.add(comic2);
        comics.add(comic3);
    }

    @Test
    public void ReturnedComics_shouldContainComics_thatWereInitializedAtTheStart() {
        List<Comic> returnedComics = comicDAO.getAll();
        assertThat(returnedComics, hasItems(
                comic1, comic2, comic3
        ));
    }

    @Test
    public void ReturnedComic_shouldContain_theSecondComic() {
        int id = 2;
        Comic returnedComic = comicDAO.getComic(id);
        assertEquals(comic2, returnedComic);
    }

    @Test
    public void EmptyList_shouldBeReturned_whenNoComicsFound() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("title", "This title doesn't exist"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(Collections.emptyList(), returnedComics);
    }

    @Test
    public void EmptyList_shouldBeReturned_whenSearchingWithEmptyListOfAttributes() {
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(Collections.emptyList());
        assertEquals(Collections.emptyList(), returnedComics);

    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByTitleWhenItIsWrittenAsIs() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("title", "Asterix"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByTitleWithSmallCase() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("title", "asterix"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByTitleWithBigCase() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("title", "ASTERIX"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByAuthorFirstName() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("author_first_name", "Albert"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(comic3, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByAuthorMiddleName() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("author_middle_name", "Jerzy"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(comic2, returnedComics.get(0));
    }

    @Test
    public void CorrectComics_ShouldBeReturned_WhenSearchingByAuthorLastName() {
        List<Pair<String, Object>> attributes = new ArrayList<>();
        attributes.add(new ImmutablePair<>("author_last_name", "Christa"));
        List<Comic> returnedComics = comicDAO.getAllWithAttributes(attributes);
        assertEquals(comic1, returnedComics.get(0));
    }
}
