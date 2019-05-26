package pawkordek.comicviewer.model;

import lombok.*;

import javax.validation.constraints.Positive;
import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@RequiredArgsConstructor
public class ComicData {
    @Positive
    @Setter(AccessLevel.NONE)
    private final long id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String title;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String path;

    public ComicData(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.title = resultSet.getString("title");
        this.path = resultSet.getString("path");
    }
}
