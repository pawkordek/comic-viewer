package pawkordek.comicviewer.dao.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLFormatterTests {

    @Test
    public void ListOfIntegers_shouldBeReturned_asAsComaSeparatedString() {
        List<Integer> integers = new ArrayList<>();
        integers.add(4);
        integers.add(12);
        integers.add(1);
        integers.add(100);
        assertEquals("4,12,1,100", SQLFormatter.prepareInClause(integers));
    }

    @Test
    public void PassingInAnEmptyList_shouldEndWithException_forPrepareInClause() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> SQLFormatter.prepareInClause(Collections.emptyList()));
    }
}
