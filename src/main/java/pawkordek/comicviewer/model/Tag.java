package pawkordek.comicviewer.model;

import lombok.*;
import org.simpleflatmapper.map.annotation.Key;

import javax.validation.constraints.Positive;

@Data
@Builder
public class Tag {

    @Key
    @Positive
    @Setter(AccessLevel.NONE)
    private final int id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String name;
}
