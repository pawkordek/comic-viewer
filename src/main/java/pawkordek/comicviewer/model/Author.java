package pawkordek.comicviewer.model;

import lombok.*;
import org.simpleflatmapper.map.annotation.Key;

import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@Data
public class Author {
    @Key
    @Positive
    @Setter(AccessLevel.NONE)
    private final int id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String firstName;

    @NonNull
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final String middleName = "";

    @NonNull
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private final String lastName = "";

    @NonNull
    @Setter(AccessLevel.NONE)
    private final List<AuthorRole> roles;
}
