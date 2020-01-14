package internetShop.service;

import internetShop.exeptions.AuthorizationException;
import internetShop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    User get(Long userId);

    User update(User user);

    void delete(Long userId);

    boolean delete(User user);

    public List<User> getAll();

    Optional<User> getByToken(String token);

    User login(String login, String password) throws AuthorizationException;
}
