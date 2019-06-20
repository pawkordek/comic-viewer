package pawkordek.comicviewer.dao.helper;

import java.util.List;

public class SQLFormatter {

    public static String prepareInClause(List<Integer> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Passd in values list cannot be empty");
        } else {
            StringBuilder valuesAsString = new StringBuilder("");
            values.forEach(value -> valuesAsString.append(value).append(","));
            valuesAsString.deleteCharAt(valuesAsString.lastIndexOf(","));
            return valuesAsString.toString();
        }
    }
}
