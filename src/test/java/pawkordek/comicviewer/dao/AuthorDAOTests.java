package pawkordek.comicviewer.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pawkordek.comicviewer.model.Author;
import pawkordek.comicviewer.model.AuthorRole;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@JdbcTest
@ComponentScan
public class AuthorDAOTests {

    @Autowired
    private AuthorDAO authorDAO;

    private static List<Author> authors = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        AuthorRole role1 = AuthorRole.builder()
                .id(1)
                .name("Writer")
                .build();

        Author author1 = Author.builder()
                .id(1)
                .firstName("Jan")
                .middleName("")
                .lastName("Kowalski")
                .roles(singletonList(role1))
                .build();

        Author author2 = Author.builder()
                .id(2)
                .firstName("John")
                .middleName("Super")
                .lastName("Smith")
                .roles(singletonList(role1))
                .build();

        authors.add(author1);
        authors.add(author2);
    }

    @Test
    public void GettingAllAuthors_shouldReturn_previouslyCreatedAuthors() {
        authorDAO.create(authors);
        List<Author> returnedAuthors = authorDAO.getAll();
        assertThat(returnedAuthors, is(not(empty())));
        assertThat(returnedAuthors, hasItems(
                allOf(
                        hasProperty("firstName", equalTo("Jan")),
                        hasProperty("middleName", equalTo("")),
                        hasProperty("lastName", equalTo("Kowalski")),
                        hasProperty("roles", hasItems(
                                allOf(
                                        hasProperty("name", equalTo("Writer"))
                                )
                                )

                        )
                ),
                allOf(
                        hasProperty("firstName", equalTo("John")),
                        hasProperty("middleName", equalTo("Super")),
                        hasProperty("lastName", equalTo("Smith")),
                        hasProperty("roles", hasItems(
                                allOf(
                                        hasProperty("name", equalTo("Writer"))
                                )
                                )

                        )
                )
                )
        );
    }
}
