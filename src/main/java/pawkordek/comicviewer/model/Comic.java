package pawkordek.comicviewer.model;

import lombok.*;
import org.simpleflatmapper.map.annotation.Key;

import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
public class Comic {

    @Key
    @Positive
    @Setter(AccessLevel.NONE)
    private final long id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String title;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String path;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final List<Author> authors;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final List<Tag> tags;
}
