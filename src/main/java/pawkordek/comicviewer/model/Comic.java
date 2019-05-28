package pawkordek.comicviewer.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Comic {
    @NonNull
    @Setter(AccessLevel.NONE)
    private final ComicData data;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final List<Author> authors;
}
