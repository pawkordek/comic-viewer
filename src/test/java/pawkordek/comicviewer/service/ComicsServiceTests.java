package pawkordek.comicviewer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.Author;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.model.ComicData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;

@RunWith(SpringRunner.class)
@ComponentScan
@SpringBootTest
public class ComicsServiceTests {

    @Autowired
    private ComicsService comicsService;

    private List<Comic> comics = new ArrayList<>();

    @Before
    public void setUp() {
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

        ComicData comicData3 = ComicData.builder()
                .id(3)
                .title("Asterix")
                .path("asterix")
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

        Author author3 = Author.builder()
                .id(3)
                .firstName("René")
                .middleName("")
                .lastName("Goscinny")
                .build();

        Author author4 = Author.builder()
                .id(4)
                .firstName("Albert")
                .middleName("")
                .lastName("Uderzo")
                .build();

        Comic comic1 = Comic.builder()
                .data(comicData1)
                .authors(Arrays.asList(author1))
                .build();

        Comic comic2 = Comic.builder()
                .data(comicData2)
                .authors(Arrays.asList(author2))
                .build();

        Comic comic3 = Comic.builder()
                .data(comicData3)
                .authors(Arrays.asList(author3, author4))
                .build();

        comics.add(comic1);
        comics.add(comic2);
        comics.add(comic3);
    }

    @Test
    public void ReturnedComics_shouldContainComics_thatWereInitializedAtTheStart() {
        List<Comic> returnedComics = comicsService.getAllComics();
        Assert.assertThat(returnedComics, hasItems(
                comics.get(0), comics.get(1), comics.get(2)
        ));
    }
}
