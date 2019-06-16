package pawkordek.comicviewer.model;

import lombok.*;
import org.simpleflatmapper.map.annotation.Column;
import org.simpleflatmapper.map.annotation.Key;

import javax.validation.constraints.Positive;

@Data
@Builder
public class User {

    @Key
    @Positive
    @Setter(AccessLevel.NONE)
    private final int id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String name;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String login;

    @NonNull
    @Setter(AccessLevel.NONE)
    @Column("pass")
    private final String password;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String type;
}
