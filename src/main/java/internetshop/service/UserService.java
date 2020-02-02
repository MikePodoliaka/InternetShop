package internetshop.service;

import internetshop.exceptions.AuthenticationException;
import internetshop.exceptions.DataProcessingExeption;
import internetshop.model.User;

public interface UserService extends GenericService<User, Long> {

    User login(String login, String password)
            throws AuthenticationException, DataProcessingExeption;
}