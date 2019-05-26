package pawkordek.comicviewer.model;

import lombok.*;

import javax.validation.constraints.Positive;

@Builder
@Data
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
}
