package pawkordek.comicviewer.helper;

import pawkordek.comicviewer.model.User;

public class ExampleUsersProvider {
    public static final User user = User.builder()
            .id(1)
            .name("user")
            .login("user")
            .password("$2a$10$eQBuCI2UDAFdU5nqYs.gbuJeWwIVgIrWJNkyHyMQFDU.pqRMEhWpO")
            .type("user")
            .build();

    public static final User admin = User.builder()
            .id(2)
            .name("admin")
            .login("admin")
            .password("$2a$10$LCqdDq7cstdt4in8sIMdxev3PdnzpA6pvM7RawGzui8A/b9i0061y")
            .type("admin")
            .build();
}
