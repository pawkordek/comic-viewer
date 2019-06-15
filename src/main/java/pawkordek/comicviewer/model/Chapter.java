package pawkordek.comicviewer.model;

import lombok.*;
import org.simpleflatmapper.map.annotation.Key;

import javax.validation.constraints.Positive;

@Data
@Builder
public class Chapter {

    @Key
    @Positive
    @Setter(AccessLevel.NONE)
    private final int id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String title;

    @Setter(AccessLevel.NONE)
    private final int amountOfPages;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String path;
}
