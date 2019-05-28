package pawkordek.comicviewer.dao.helper;

import java.util.List;

public class SQLFormatter {

    public static String prepareInClause(List<Integer> values) {
        StringBuilder valuesAsString = new StringBuilder("");
        values.forEach(value -> valuesAsString.append(value).append(","));
        valuesAsString.deleteCharAt(valuesAsString.lastIndexOf(","));
        return valuesAsString.toString();
    }
}
