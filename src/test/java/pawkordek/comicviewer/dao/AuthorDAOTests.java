package pawkordek.comicviewer.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.Author;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class AuthorDAOTests {

    @Autowired
    private AuthorDAO authorDAO;

    private List<Author> authors = new ArrayList<>();

    @Before
    public void setUp() throws SQLException {
        Author author1 = Author.builder()
                .id(1)
                .firstName("Jan")
                .middleName("")
                .lastName("Kowalski")
                .build();

        Author author2 = Author.builder()
                .id(2)
                .firstName("John")
                .middleName("Super")
                .lastName("Smith")
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
                        hasProperty("lastName", equalTo("Kowalski"))
                ),
                allOf(
                        hasProperty("firstName", equalTo("John")),
                        hasProperty("middleName", equalTo("Super")),
                        hasProperty("lastName", equalTo("Smith"))
                )
                )
        );
    }
}
