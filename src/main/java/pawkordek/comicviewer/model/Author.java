package pawkordek.comicviewer.model;

import lombok.*;

import javax.validation.constraints.Positive;
import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
@Data
@RequiredArgsConstructor
public class Author {
    @Positive
    @Setter(AccessLevel.NONE)
    private final int id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String firstName;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String middleName;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String lastName;

    public Author(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.firstName = resultSet.getString("first_name");
        this.middleName = resultSet.getString("middle_name");
        this.lastName = resultSet.getString("last_name");
    }
}
