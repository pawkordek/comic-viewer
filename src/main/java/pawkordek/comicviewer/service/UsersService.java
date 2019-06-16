package pawkordek.comicviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pawkordek.comicviewer.dao.UserDAO;
import pawkordek.comicviewer.model.User;
import pawkordek.comicviewer.model.UserAuthenticationDetails;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    public User getUserWithName(String name) {
        return userDAO.getUserWithName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserWithName(username);
        return new UserAuthenticationDetails(user);
    }
}
