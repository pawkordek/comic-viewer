package pawkordek.comicviewer.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pawkordek.comicviewer.model.ComicData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan
public class ComicDataDAOTests {

    @Autowired
    private ComicDataDAO comicDataDAO;

    private List<ComicData> comicData = new ArrayList<>();

    @Before
    public void setUp() throws SQLException {
        ComicData comicData1 = ComicData.builder()
                .id(1)
                .title("Asterix & Obelix")
                .path("asterix")
                .build();

        ComicData comicData2 = ComicData.builder()
                .id(2)
                .title("Marzi")
                .path("marzi")
                .build();

        comicData.add(comicData1);
        comicData.add(comicData2);
    }

    @Test
    public void GettingAllComicsData_shouldReturn_thePreviouslyCreatedComicsData() {
        comicDataDAO.create(comicData);
        List<ComicData> returnedComicData = comicDataDAO.getAll();
        assertThat(returnedComicData, is(not(empty())));
        assertThat(returnedComicData, hasItems(
                allOf(
                        hasProperty("title", equalTo("Asterix & Obelix")),
                        hasProperty("path", equalTo("asterix"))
                ),
                allOf(
                        hasProperty("title", equalTo("Marzi")),
                        hasProperty("path", equalTo("marzi"))
                )
                )
        );
    }
}

