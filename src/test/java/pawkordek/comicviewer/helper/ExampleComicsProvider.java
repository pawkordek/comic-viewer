package pawkordek.comicviewer.helper;

import pawkordek.comicviewer.model.Author;
import pawkordek.comicviewer.model.Comic;
import pawkordek.comicviewer.model.ComicData;

import java.util.Arrays;

import static java.util.Collections.singletonList;

public class ExampleComicsProvider {
    public final static ComicData comicData1 = ComicData.builder()
            .id(1)
            .title("Kajko i Kokosz")
            .path("kajko_kokosz")
            .build();

    public final static ComicData comicData2 = ComicData.builder()
            .id(2)
            .title("Tytus, Romek i Atomek")
            .path("tytus_romek_atomek")
            .build();

    public final static ComicData comicData3 = ComicData.builder()
            .id(3)
            .title("Asterix")
            .path("asterix")
            .build();

    public final static Author author1 = Author.builder()
            .id(1)
            .firstName("Janusz")
            .middleName("")
            .lastName("Christa")
            .build();

    public final static Author author2 = Author.builder()
            .id(2)
            .firstName("Henryk")
            .middleName("Jerzy")
            .lastName("Chmielewski")
            .build();

    public final static Author author3 = Author.builder()
            .id(3)
            .firstName("René")
            .middleName("")
            .lastName("Goscinny")
            .build();

    public final static Author author4 = Author.builder()
            .id(4)
            .firstName("Albert")
            .middleName("")
            .lastName("Uderzo")
            .build();

    public final static Comic comic1 = Comic.builder()
            .data(comicData1)
            .authors(singletonList(author1))
            .build();

    public final static Comic comic2 = Comic.builder()
            .data(comicData2)
            .authors(singletonList(author2))
            .build();

    public final static Comic comic3 = Comic.builder()
            .data(comicData3)
            .authors(Arrays.asList(author3, author4))
            .build();
}
