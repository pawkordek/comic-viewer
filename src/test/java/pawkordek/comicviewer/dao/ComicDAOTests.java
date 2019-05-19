package pawkordek.comicviewer.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.Comic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class ComicDAOTests {

    @Autowired
    private ComicDAO comicDAO;

    private List<Comic> comics = new ArrayList<>();

    @Before
    public void setUp() throws SQLException {
        Comic comic1 = Comic.builder()
                .id(1)
                .title("Asterix & Obelix")
                .path("asterix")
                .build();

        Comic comic2 = Comic.builder()
                .id(2)
                .title("Marzi")
                .path("marzi")
                .build();

        comics.add(comic1);
        comics.add(comic2);
    }

    @Test
    public void GettingAllComics_shouldReturn_thePreviouslyCreatedComics() {
        comicDAO.create(comics);
        List<Comic> returnedComics = comicDAO.getAll();
        assertThat(returnedComics, is(not(empty())));
        assertThat(returnedComics, hasSize(2));
        Comic comic1 = returnedComics.get(0);
        assertThat(comic1, hasProperty("title", equalTo("Asterix & Obelix")));
        assertThat(comic1, hasProperty("path", equalTo("asterix")));
        Comic comic2 = returnedComics.get(1);
        assertThat(comic2, hasProperty("title", equalTo("Marzi")));
        assertThat(comic2, hasProperty("path", equalTo("marzi")));
    }

    @Test
    public void GettingAllComics_shouldReturnEmptyList_whenThereAreNoComics() {
        List<Comic> returnedComics = comicDAO.getAll();
        assertThat(returnedComics, is(not(nullValue())));
        assertThat(returnedComics, is(empty()));
    }
}
