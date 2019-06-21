package pawkordek.comicviewer.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.UserDAO;
import pawkordek.comicviewer.model.User;
import pawkordek.comicviewer.model.UserAuthenticationDetails;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersService implements UserDetailsService {
    @NonNull
    private final UserDAO userDAO;

    @NonNull
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserWithName(String name) {
        return userDAO.getUserWithName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserWithName(username);
        return new UserAuthenticationDetails(user);
    }

    public List<String> getErrorsInUserData(String username, String login, String password, String passwordConfirm) {
        List<String> validationErrors = new ArrayList<>();
        if (StringUtils.isBlank(username)) {
            validationErrors.add("usernameInvalid");
        }
        if (StringUtils.isBlank(login)) {
            validationErrors.add("loginInvalid");
        }
        if (password.equals(passwordConfirm)) {
            if (StringUtils.isBlank(password)) {
                validationErrors.add("passwordInvalid");
            }
            if (StringUtils.isBlank(passwordConfirm)) {
                validationErrors.add("passwordConfirmInvalid");
            }
        } else {
            validationErrors.add("passwordsDoNotMatch");
        }
        return validationErrors;
    }

    public void addNewUser(String name, String login, String password) {
        User user = User.builder()
                .id(0)
                .name(name)
                .login(login)
                .password(passwordEncoder.encode(password))
                .type("user")
                .build();
        userDAO.createUser(user);
    }
}
