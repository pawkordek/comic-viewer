package pawkordek.comicviewer.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.Author;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.model.ComicData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
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
        ComicData comicData1 = ComicData.builder()
                .id(1)
                .title("Kajko i Kokosz")
                .path("kajko_kokosz")
                .build();

        ComicData comicData2 = ComicData.builder()
                .id(2)
                .title("Tytus, Romek i Atomek")
                .path("tytus_romek_atomek")
                .build();

        Author author1 = Author.builder()
                .id(1)
                .firstName("Janusz")
                .middleName("")
                .lastName("Christa")
                .build();

        Author author2 = Author.builder()
                .id(2)
                .firstName("Henryk")
                .middleName("Jerzy")
                .lastName("Chmielewski")
                .build();

        Comic comic1 = Comic.builder()
                .data(comicData1)
                .author(author1)
                .build();

        Comic comic2 = Comic.builder()
                .data(comicData2)
                .author(author2)
                .build();

        comics.add(comic1);
        comics.add(comic2);
    }

    @Test
    public void GettingAllComics_shouldReturn_ComicsCreatedOnTestStartup() {
        List<Comic> returnedComics = comicDAO.getAll();
        assertThat(returnedComics, is(not(empty())));
        assertThat(returnedComics, hasItems(
                comics.get(0), comics.get(1)
        ));
    }
}
