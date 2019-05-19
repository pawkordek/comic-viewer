package pawkordek.comicviewer.model;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@RequiredArgsConstructor
public class Comic {
    @NonNull
    @Setter(AccessLevel.NONE)
    private long id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private String title;

    @NonNull
    @Setter(AccessLevel.NONE)
    private String path;

    public Comic(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.title = resultSet.getString("title");
        this.path = resultSet.getString("path");
    }
}
