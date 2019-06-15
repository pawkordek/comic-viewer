package pawkordek.comicviewer.helper;

import pawkordek.comicviewer.model.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class ExampleComicsProvider {
    public final static AuthorRole authorRole1 = AuthorRole.builder()
            .id(1)
            .name("Writer")
            .build();

    public final static AuthorRole authorRole2 = AuthorRole.builder()
            .id(2)
            .name("Artist")
            .build();

    public final static Author author1 = Author.builder()
            .id(1)
            .firstName("Janusz")
            .lastName("Christa")
            .roles(asList(authorRole1, authorRole2))
            .build();

    public final static Author author2 = Author.builder()
            .id(2)
            .firstName("Henryk")
            .middleName("Jerzy")
            .lastName("Chmielewski")
            .roles(asList(authorRole1, authorRole2))
            .build();

    public final static Author author3 = Author.builder()
            .id(3)
            .firstName("René")
            .lastName("Goscinny")
            .roles(singletonList(authorRole1))
            .build();

    public final static Author author4 = Author.builder()
            .id(4)
            .firstName("Albert")
            .lastName("Uderzo")
            .roles(singletonList(authorRole2))
            .build();

    public final static Tag tag1 = Tag.builder()
            .id(1)
            .name("Polish")
            .build();

    public final static Tag tag2 = Tag.builder()
            .id(2)
            .name("French")
            .build();

    public final static Chapter chapter1 = Chapter.builder()
            .id(1)
            .title("KK1")
            .amountOfPages(10)
            .path("kk1")
            .build();

    public final static Chapter chapter2 = Chapter.builder()
            .id(2)
            .title("KK2")
            .amountOfPages(20)
            .path("kk2")
            .build();

    public final static Chapter chapter3 = Chapter.builder()
            .id(3)
            .title("TRA")
            .amountOfPages(30)
            .path("tra")
            .build();

    public final static Chapter chapter4 = Chapter.builder()
            .id(4)
            .title("chapter 1")
            .amountOfPages(5)
            .path("chapter_1")
            .build();

    public final static Comic comic1 = Comic.builder()
            .id(1)
            .title("Kajko i Kokosz")
            .path("kajko_kokosz")
            .authors(singletonList(author1))
            .tags(singletonList(tag1))
            .chapters(asList(chapter1, chapter2))
            .build();

    public final static Comic comic2 = Comic.builder()
            .id(2)
            .title("Tytus, Romek i Atomek")
            .path("tytus_romek_atomek")
            .authors(singletonList(author2))
            .tags(singletonList(tag1))
            .chapters(singletonList(chapter3))
            .build();

    public final static Comic comic3 = Comic.builder()
            .id(3)
            .title("Asterix")
            .path("asterix")
            .authors(asList(author3, author4))
            .tags(singletonList(tag2))
            .chapters(singletonList(chapter4))
            .build();
}
