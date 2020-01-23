package internetShop.dao;

import internetShop.exeptions.AuthorizationException;
import internetShop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    User update(User user);

    void delete(Long userId);

    boolean delete(User user);

    List<User> getAll();

    User login(String login, String password) throws AuthorizationException;

    Optional<User> getByToken(String token);
}
