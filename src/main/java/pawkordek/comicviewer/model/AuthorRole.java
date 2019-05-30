package pawkordek.comicviewer.model;

import lombok.*;

import javax.validation.constraints.Positive;

@Builder
@RequiredArgsConstructor
@Data
public class AuthorRole {
    @Positive
    @Setter(AccessLevel.NONE)
    private final int id;

    @NonNull
    @Setter(AccessLevel.NONE)
    private final String name;
}
