package pawkordek.comicviewer.model;

import lombok.*;

@Data
@Builder
public class Comic {
    @NonNull
    @Setter(AccessLevel.NONE)
    private final ComicData data;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final Author author;
}
